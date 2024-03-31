package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.customer.CreateCustomerRequest;
import com.etiya.rentACar.business.dtos.requests.customer.UpdateCustomerRequest;
import com.etiya.rentACar.business.dtos.responses.customer.CreatedCustomerResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerListResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerResponse;
import com.etiya.rentACar.business.dtos.responses.customer.UpdatedCustomerResponse;
import com.etiya.rentACar.entities.Customer;

import java.util.List;

public interface CustomerService {

    CreatedCustomerResponse add (CreateCustomerRequest customer);

    UpdatedCustomerResponse update (UpdateCustomerRequest customer);

    List<GetCustomerListResponse> getAll();

    GetCustomerResponse getById(int id);

//    Customer getCustomerById(int id);
    void customerIdMustBeExists(int id);
    void delete(int id);
}
