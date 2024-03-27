package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.City;
import com.etiya.rentACar.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository  extends JpaRepository<City,Integer> {
}
