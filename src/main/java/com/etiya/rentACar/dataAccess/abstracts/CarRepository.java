package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Integer> {
    Optional<Car> findByPlateIgnoreCase(String plate);

}
