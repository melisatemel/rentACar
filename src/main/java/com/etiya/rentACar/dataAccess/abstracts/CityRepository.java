package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.City;
import com.etiya.rentACar.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository  extends JpaRepository<City,Integer> {

    Optional<City> findByNameIgnoreCase(String name);
}
