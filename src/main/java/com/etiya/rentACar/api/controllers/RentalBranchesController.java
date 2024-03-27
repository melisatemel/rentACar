package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.RentalBranchService;
import com.etiya.rentACar.business.dtos.requests.rentalbranch.CreateRentalBranchRequest;
import com.etiya.rentACar.business.dtos.requests.rentalbranch.UpdateRentalBranchRequest;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.CreatedRentalBranchResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.GetRentalBranchListResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.GetRentalBranchResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.UpdatedRentalBranchResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rentalbranches")
public class RentalBranchesController {
    private RentalBranchService rentalBranchService;

    @GetMapping
    public List<GetRentalBranchListResponse> getAll(){
        return rentalBranchService.getAll();
    }

    @GetMapping("/{id}")
    public GetRentalBranchResponse getById(@PathVariable int id){
        return rentalBranchService.getById(id);
    }

    @PostMapping
    public CreatedRentalBranchResponse add(@Valid @RequestBody CreateRentalBranchRequest createRentalBranchRequest){
        return rentalBranchService.add(createRentalBranchRequest);
    }

    @PutMapping
    public UpdatedRentalBranchResponse update(@Valid @RequestBody UpdateRentalBranchRequest updateRentalBranchRequest){
        return rentalBranchService.update(updateRentalBranchRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        rentalBranchService.delete(id);
    }
}
