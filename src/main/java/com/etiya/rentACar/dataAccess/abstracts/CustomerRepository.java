package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Optional <Customer> findByNationalNo(String nationalId);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);
}
