package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private RentalRepository rentalRepository;
    public void rentalIdCannotBeDuplicated(int rentalId){
        if(rentalRepository.existsById(rentalId)){
            throw new BusinessException("Rental Exists");
        }

    }

    public void rentalIdMustBeExist(int rentalId){
        if(!rentalRepository.existsById(rentalId)){
            throw new BusinessException("Rental Not Found");
        }
    }
}
