package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Bir interface başka bir interface i extends eder.
//Brand in primary key'i (id) si hangi veri tipinde ise onu yaz <Brand,Integer>
public interface BrandRepository extends JpaRepository<Brand,Integer> // Veri tabanındaki temel operasyonları hazırlar
{
    //verilerle iletişim sağlanır
        //SQL işlemleri burada yapılır (veri işlemleri)
    Optional<Brand> findByNameIgnoreCase(String name);  //Brand entititeste name göre bulma
}
