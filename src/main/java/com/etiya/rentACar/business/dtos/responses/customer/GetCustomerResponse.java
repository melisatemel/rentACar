package com.etiya.rentACar.business.dtos.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerResponse {

    private int id;
    private String fullName;
    private String email;
    private LocalDate birthDate;
}
