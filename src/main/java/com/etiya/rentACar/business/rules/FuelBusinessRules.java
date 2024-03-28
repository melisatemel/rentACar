package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.FuelRepository;
import com.etiya.rentACar.entities.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FuelBusinessRules {
    private FuelRepository fuelRepository;

    public void fuelNameCannotBeDuplicated(String fuelName){
        Optional<Fuel> fuel = fuelRepository.findByNameIgnoreCase(fuelName);
        if(fuel.isPresent()){
            throw new BusinessException("Fuel Exists");
        }
    }

    public void fuelIdMustBeExists(int id){
        if(!fuelRepository.existsById(id)){
            throw new BusinessException("Fuel id must be exists");
        }
    }
}
