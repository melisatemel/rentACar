package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.messages.RentalMessages;
import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private RentalRepository rentalRepository;
    private CarService carService;

    public void carStatusMustBeTrue(int carId){
        if(!carService.carStatusIsAvailable(carId)){
            throw new BusinessException(RentalMessages.CarStatusMustBeTrue);
        }
    }

    public void rentalIdMustBeExist(int rentalId){
        if(!rentalRepository.existsById(rentalId)){
            throw new BusinessException("Rental Not Found");
        }
    }

    public void checkIfCustomerHasDeliveredRentalCar(int customerId){
        if(rentalRepository.existsByCustomerIdAndReturnDateIsNull(customerId)){
            throw new BusinessException("Customer has not delivered the car yet");
        }
    }
}
