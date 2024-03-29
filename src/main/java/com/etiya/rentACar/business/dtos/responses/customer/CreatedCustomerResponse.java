package com.etiya.rentACar.business.dtos.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedCustomerResponse {
    private int id;
    private String fullName;
    private String email;
    private String nationalNo;
    private LocalDate birthDate;
    private LocalDateTime createdDate;
}
