package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CustomerService;
import com.etiya.rentACar.business.dtos.requests.customer.CreateCustomerRequest;
import com.etiya.rentACar.business.dtos.requests.customer.UpdateCustomerRequest;
import com.etiya.rentACar.business.dtos.responses.customer.CreatedCustomerResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerListResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerResponse;
import com.etiya.rentACar.business.dtos.responses.customer.UpdatedCustomerResponse;
import com.etiya.rentACar.business.rules.CustomerBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.CustomerRepository;
import com.etiya.rentACar.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerBusinessRules customerBusinessRules;

    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest customer) {
       customerBusinessRules.customerNationalIdCannotBeDuplicated(customer.getNationalNo());
        customerBusinessRules.customerEmailCannotBeDuplicated(customer.getEmail());
        customerBusinessRules.customerPhoneCannotBeDuplicated(customer.getPhone());
        customerBusinessRules.customerBirthDateCannotBeYoungerThan18(customer.getBirthDate());

        Customer customerToAdd = modelMapperService.forRequest().map(customer, Customer.class);
        Customer createdCustomer = customerRepository.save(customerToAdd);

        return modelMapperService.forResponse().map(createdCustomer, CreatedCustomerResponse.class);
    }

    @Override
    public UpdatedCustomerResponse update(UpdateCustomerRequest customer) {
        customerBusinessRules.customerIdMustBeExists(customer.getId());
        customerBusinessRules.customerBirthDateCannotBeYoungerThan18(customer.getBirthDate());

        Customer existingCustomer = customerRepository.findById(customer.getId()).get();
        Customer updatedCustomer = modelMapperService.forRequest().map(customer, Customer.class);
        updatedCustomer.setCreatedDate(existingCustomer.getCreatedDate());
        updatedCustomer = customerRepository.save(updatedCustomer);

        return modelMapperService.forResponse().map(updatedCustomer, UpdatedCustomerResponse.class);
    }

    @Override
    public List<GetCustomerListResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .filter(customer -> customer.getDeletedDate() == null)
                .map(customer -> modelMapperService.forResponse()
                        .map(customer, GetCustomerListResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetCustomerResponse getById(int id) {
        customerBusinessRules.customerIdMustBeExists(id);
        Customer customer = customerRepository.findById(id).get();
        return modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
    }

//    @Override
//    public Customer getCustomerById(int id) {
//        customerBusinessRules.customerIdMustBeExists(id);
//        return customerRepository.findById(id).get();
//    }

    @Override
    public void customerIdMustBeExists(int id) {
        customerBusinessRules.customerIdMustBeExists(id);
    }

    @Override
    public void delete(int id) {
        customerBusinessRules.customerIdMustBeExists(id);
        Customer deletedCustomer = customerRepository.findById(id).get();
        deletedCustomer.setDeletedDate(LocalDateTime.now());
        customerRepository.save(deletedCustomer);
    }
}
