package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.RentalBranchRepository;
import com.etiya.rentACar.entities.RentalBranch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalBranchBusinessRules {

    private RentalBranchRepository rentalBranchRepository;
    public void isRentalBranchAvailable(int id){
        Optional<RentalBranch> rentalBranch = rentalBranchRepository.findById(id);
        if(rentalBranch.isEmpty()){
            throw new BusinessException("RentalBranch not found");
        }
    }
}
