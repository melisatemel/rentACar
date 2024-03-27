package com.etiya.rentACar.business.dtos.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCustomerResponse {

    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String nationalId;
    private LocalDateTime updatedDate;
    private LocalDateTime birthDate;

}
