package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private RentalRepository rentalRepository;
    private CarService carService;
    public void rentalIdCannotBeDuplicated(int rentalId){
        if(rentalRepository.existsById(rentalId)){
            throw new BusinessException("Rental Exists");
        }

    }

    public void carStatusMustBeTrue(int carId){
        if(!carService.carStatusIsAvailable(carId)){
            throw new BusinessException("Car Must Be Available");
        }
    }

    public void rentalIdMustBeExist(int rentalId){
        if(!rentalRepository.existsById(rentalId)){
            throw new BusinessException("Rental Not Found");
        }
    }

}
