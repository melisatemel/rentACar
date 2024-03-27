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
        customerBusinessRules.customerNationalIdCannotBeDuplicated(customer.getNationalId());
        customerBusinessRules.customerEmailCannotBeDuplicated(customer.getEmail());
        customerBusinessRules.customerPhoneCannotBeDuplicated(customer.getPhone());
        customerBusinessRules.customerBirthDateCannotBeOlderThan18(customer.getBirthDate());

        Customer customerToAdd = modelMapperService.forRequest().map(customer, Customer.class);
        customerRepository.save(customerToAdd);

        CreatedCustomerResponse response = modelMapperService.forResponse().map(customerToAdd, CreatedCustomerResponse.class);
        return response;
    }

    @Override
    public UpdatedCustomerResponse update(UpdateCustomerRequest customer) {
        customerBusinessRules.customerNationalIdCannotBeDuplicated(customer.getNationalId());
        customerBusinessRules.customerEmailCannotBeDuplicated(customer.getEmail());
        customerBusinessRules.customerPhoneCannotBeDuplicated(customer.getPhone());
        customerBusinessRules.customerBirthDateCannotBeOlderThan18(customer.getBirthDate());

        Customer customerToUpdate = customerRepository.findById(customer.getId()).orElseThrow(() -> new IllegalArgumentException("Customer not found!"));
        modelMapperService.forRequest().map(customer, customerToUpdate);
        customerRepository.save(customerToUpdate);

        UpdatedCustomerResponse response = modelMapperService.forResponse().map(customerToUpdate, UpdatedCustomerResponse.class);
        return response;
    }

    @Override
    public List<GetCustomerListResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<GetCustomerListResponse> customerListResponses = customers.stream()
                .map(customer -> modelMapperService.forResponse()
                        .map(customer, GetCustomerListResponse.class)).collect(Collectors.toList());

        return customerListResponses;
    }

    @Override
    public GetCustomerResponse getById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found!"));
        GetCustomerResponse response = modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }
}
