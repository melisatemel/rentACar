package com.etiya.rentACar.business.dtos.requests.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {

    @NotBlank(message = "fullName cannot be empty!")
    private String fullName;

    @NotBlank(message = "email cannot be empty!")
    private String email;

    @NotBlank(message = "phone cannot be empty!")
    private String phone;

    @NotBlank(message = "nationalNo cannot be empty!")
    private String nationalNo;

    @NotNull(message = "birthDate cannot be empty!")
    private LocalDate birthDate;
}
