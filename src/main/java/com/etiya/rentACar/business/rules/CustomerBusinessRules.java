package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.CustomerRepository;
import com.etiya.rentACar.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {

    private CustomerRepository customerRepository;

    public void customerNationalIdCannotBeDuplicated(String nationalNo){
        Optional<Customer> customer = customerRepository.findByNationalNo(nationalNo);
        if(customer.isPresent()){
            throw new BusinessException("Customer NationalId Exists");
        }
    }

    public void customerEmailCannotBeDuplicated(String email){
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if(customer.isPresent()){
            throw new BusinessException("Customer Email Exists");
        }
    }

    public void customerPhoneCannotBeDuplicated(String phone){
        Optional<Customer> customer = customerRepository.findByPhone(phone);
        if(customer.isPresent()){
            throw new BusinessException("Customer Phone Exists");
        }
    }

    public void customerBirthDateCannotBeOlderThan18(LocalDate birthDate){
        if(birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new BusinessException("Customer must be older than 18 years old");
        }
    }

    public void customerIdMustBeExists(int id) {
        if (!customerRepository.existsById(id)) {
            throw new BusinessException("Customer id must be exists");
        }
    }
}
