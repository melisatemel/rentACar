package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.rentalbranch.CreateRentalBranchRequest;
import com.etiya.rentACar.business.dtos.requests.rentalbranch.UpdateRentalBranchRequest;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.CreatedRentalBranchResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.GetRentalBranchListResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.GetRentalBranchResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.UpdatedRentalBranchResponse;

import java.util.List;

public interface RentalBranchService {
    public List<GetRentalBranchListResponse> getAll();

    GetRentalBranchResponse getById(int id);

    CreatedRentalBranchResponse add(CreateRentalBranchRequest createRentalBranchRequest);

    UpdatedRentalBranchResponse update(UpdateRentalBranchRequest updateRentalBranchRequest);

    void rentalBranchIdMustBeExists(int id);
    void delete(int id);
}
