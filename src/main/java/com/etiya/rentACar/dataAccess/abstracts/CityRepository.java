package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.City;
import com.etiya.rentACar.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository  extends JpaRepository<City,Integer> {
    //verilerle iletişim sağlanır
    //SQL işlemleri burada yapılır (veri işlemleri)
    Optional<City> findByNameIgnoreCase(String name);  //Brand entititeste name göre bulma
}
