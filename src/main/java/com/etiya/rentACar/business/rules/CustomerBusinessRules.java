package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.business.messages.CustomerMessages;
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
            throw new BusinessException(CustomerMessages.customerNationalIdCannotBeDuplicated);
        }
    }

    public void customerEmailCannotBeDuplicated(String email){
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if(customer.isPresent()){
            throw new BusinessException(CustomerMessages.customerEmailCannotBeDuplicated);
        }
    }

    public void customerPhoneCannotBeDuplicated(String phone){
        Optional<Customer> customer = customerRepository.findByPhone(phone);
        if(customer.isPresent()){
            throw new BusinessException(CustomerMessages.customerPhoneCannotBeDuplicated);
        }
    }

    public void customerBirthDateCannotBeYoungerThan18(LocalDate birthDate){
        if(birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new BusinessException(CustomerMessages.customerBirthDateCannotBeYoungerThan18);
        }
    }

    public void customerIdMustBeExists(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new BusinessException(CustomerMessages.customerNotFound);
        }
        else if(customer.get().getDeletedDate() != null){
            throw new BusinessException(CustomerMessages.customerNotFound);
        }
    }
}
