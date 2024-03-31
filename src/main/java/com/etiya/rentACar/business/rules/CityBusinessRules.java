package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.CityRepository;
import com.etiya.rentACar.entities.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CityBusinessRules {
    private CityRepository cityRepository;

    public void cityNAmeCannotBeDuplicated(String cityName){
        Optional<City> city = cityRepository.findByNameIgnoreCase(cityName.trim());

        if(city.isPresent()){
            throw new BusinessException("City Exists!");
        }
    }

    public void isCityAvailable(int id){
        Optional<City> city = cityRepository.findById(id);
        if(city.isEmpty()){
            throw new BusinessException("City not found");
        }
    }

    public void cityIdMustBeExists(int id){
        Optional<City> city = cityRepository.findById(id);
        if(city.isEmpty()){
            throw new BusinessException("City not found");
        }
        else if(city.get().getDeletedDate() != null){
            throw new BusinessException("City not found");
        }
    }

}
