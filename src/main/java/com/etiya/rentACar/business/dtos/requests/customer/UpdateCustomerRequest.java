package com.etiya.rentACar.business.dtos.requests.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String nationalNo;
    private LocalDate birthDate;
}
