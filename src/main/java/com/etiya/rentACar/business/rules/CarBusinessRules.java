package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.CarRepository;
import com.etiya.rentACar.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class CarBusinessRules {
    private CarRepository carRepository;
    public void  carPlateCannotBeDuplicated(String plate){
        Optional<Car> car = carRepository.findByPlateIgnoreCase(plate); //bu değeri bulamayadabilirsin ama bulursan car 'a eşitle

        if(car.isPresent()){    //içi doluysa true döner
            throw new BusinessException("Car Plate Exists");
        }
    }
    public void carModelYearCheck (Integer modelYear){
        if(modelYear>2024 || modelYear<1900){
            throw new BusinessException("Model Year cannot exceed 2024");
        }
    }
    public void carDailyPriceCheck(double dailyPrice){
        if(dailyPrice<=0){
            throw new BusinessException("Daily Price must be bigger than 0");
        }
    }

    public void carIdMustBeExists(int id) {
        if (!carRepository.existsById(id)) {
            throw new BusinessException("Car id must be exists");
        }
    }
}
