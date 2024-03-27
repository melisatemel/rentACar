package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.RentalBranchService;
import com.etiya.rentACar.business.dtos.requests.rentalbranch.CreateRentalBranchRequest;
import com.etiya.rentACar.business.dtos.requests.rentalbranch.UpdateRentalBranchRequest;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandListResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.CreatedRentalBranchResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.GetRentalBranchListResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.GetRentalBranchResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.UpdatedRentalBranchResponse;
import com.etiya.rentACar.business.rules.RentalBranchBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.RentalBranchRepository;
import com.etiya.rentACar.entities.RentalBranch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalBranchManager implements RentalBranchService {

    private RentalBranchRepository rentalBranchRepository;
    private ModelMapperService modelMapperService;
    private RentalBranchBusinessRules rentalBranchBusinessRules;

    @Override
    public List<GetRentalBranchListResponse> getAll() {
        List<RentalBranch> rentalBranches = rentalBranchRepository.findAll();
        List<GetRentalBranchListResponse> getRentalBranchListResponses = rentalBranches.stream()
                .map(rentalBranch -> modelMapperService.forResponse()
                        .map(rentalBranch,GetRentalBranchListResponse.class)).collect(Collectors.toList());
        return getRentalBranchListResponses;
    }

    @Override
    public GetRentalBranchResponse getById(int id) {
        rentalBranchBusinessRules.isRentalBranchAvailable(id);
        RentalBranch rentalBranch = rentalBranchRepository.findById(id).get();
        GetRentalBranchResponse getRentalBranchResponse = modelMapperService.forResponse()
                .map(rentalBranch, GetRentalBranchResponse.class);
        return getRentalBranchResponse;
    }

    @Override
    public CreatedRentalBranchResponse add(CreateRentalBranchRequest createRentalBranchRequest) {
        RentalBranch rentalBranch = modelMapperService.forRequest().map(createRentalBranchRequest,RentalBranch.class);
        RentalBranch savedRentalBranch = rentalBranchRepository.save(rentalBranch);
        CreatedRentalBranchResponse createdRentalBranchResponse = modelMapperService.forResponse()
                .map(savedRentalBranch,CreatedRentalBranchResponse.class);
        return createdRentalBranchResponse;
    }

    @Override
    public UpdatedRentalBranchResponse update(UpdateRentalBranchRequest updateRentalBranchRequest) {
        rentalBranchBusinessRules.isRentalBranchAvailable(updateRentalBranchRequest.getId());
        RentalBranch rentalBranch = rentalBranchRepository.findById(updateRentalBranchRequest.getId()).get();
        RentalBranch mappedRentalBranch = modelMapperService.forRequest().map(updateRentalBranchRequest,RentalBranch.class);
        mappedRentalBranch.setCreatedDate(rentalBranch.getCreatedDate());
        RentalBranch savedRentalBranch = rentalBranchRepository.save(mappedRentalBranch);
        UpdatedRentalBranchResponse updatedRentalBranchResponse = modelMapperService.forResponse()
                .map(savedRentalBranch,UpdatedRentalBranchResponse.class);
        return updatedRentalBranchResponse;
    }

    @Override
    public void delete(int id) {
        rentalBranchBusinessRules.isRentalBranchAvailable(id);
        RentalBranch rentalBranch = rentalBranchRepository.findById(id).get();
        rentalBranch.setDeletedDate(LocalDateTime.now());
        rentalBranchRepository.save(rentalBranch);
    }
}
