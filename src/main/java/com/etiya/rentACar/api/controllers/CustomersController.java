package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.CustomerService;
import com.etiya.rentACar.business.dtos.requests.customer.CreateCustomerRequest;
import com.etiya.rentACar.business.dtos.requests.customer.UpdateCustomerRequest;
import com.etiya.rentACar.business.dtos.responses.customer.CreatedCustomerResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerListResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerResponse;
import com.etiya.rentACar.business.dtos.responses.customer.UpdatedCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomersController {

    private CustomerService customerService;

    @GetMapping
    public List<GetCustomerListResponse> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public GetCustomerResponse getById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCustomerResponse add(@Valid@RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.add(createCustomerRequest);
    }

    @PutMapping
    public UpdatedCustomerResponse update(@Valid@RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        customerService.delete(id);
    }
}
