package com.etiya.rentACar.business.dtos.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCustomerResponse {

    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String nationalNo;
    private LocalDate birthDate;
    private LocalDateTime updatedDate;


}
