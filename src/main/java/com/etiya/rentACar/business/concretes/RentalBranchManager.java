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

        return rentalBranches.stream()
                .map(rentalBranch -> modelMapperService.forResponse()
                        .map(rentalBranch,GetRentalBranchListResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetRentalBranchResponse getById(int id) {
        rentalBranchBusinessRules.isRentalBranchAvailable(id);
        RentalBranch rentalBranch = rentalBranchRepository.findById(id).get();

        return modelMapperService.forResponse()
                .map(rentalBranch, GetRentalBranchResponse.class);
    }

    @Override
    public CreatedRentalBranchResponse add(CreateRentalBranchRequest createRentalBranchRequest) {
        rentalBranchBusinessRules.checkIfCityExists(createRentalBranchRequest.getCityId());


        RentalBranch rentalBranch = this.modelMapperService.forRequest()
                .map(createRentalBranchRequest,RentalBranch.class);
        rentalBranch.setId(0);
        RentalBranch savedRentalBranch = rentalBranchRepository.save(rentalBranch);

        return modelMapperService.forResponse()
                .map(savedRentalBranch,CreatedRentalBranchResponse.class);
    }

    @Override
    public UpdatedRentalBranchResponse update(UpdateRentalBranchRequest updateRentalBranchRequest) {
        rentalBranchBusinessRules.isRentalBranchAvailable(updateRentalBranchRequest.getId());
        rentalBranchBusinessRules.checkIfCityExists(updateRentalBranchRequest.getId());

        RentalBranch rentalBranch = rentalBranchRepository.findById(updateRentalBranchRequest.getId()).get();
        RentalBranch mappedRentalBranch = modelMapperService.forRequest()
                .map(updateRentalBranchRequest,RentalBranch.class);

        mappedRentalBranch.setCreatedDate(rentalBranch.getCreatedDate());
        RentalBranch savedRentalBranch = rentalBranchRepository.save(mappedRentalBranch);

        return modelMapperService.forResponse()
                .map(savedRentalBranch,UpdatedRentalBranchResponse.class);
    }

    @Override
    public void rentalBranchIdMustBeExists(int id) {
        rentalBranchBusinessRules.isRentalBranchAvailable(id);
    }

    @Override
    public void delete(int id) {
        //todo: soft delete için tüm deleteleri kontrol et
        rentalBranchBusinessRules.isRentalBranchAvailable(id);
        RentalBranch rentalBranch = rentalBranchRepository.findById(id).get();
        rentalBranch.setDeletedDate(LocalDateTime.now());
        rentalBranchRepository.save(rentalBranch);
    }
}
