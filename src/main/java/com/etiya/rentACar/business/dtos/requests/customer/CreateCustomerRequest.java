package com.etiya.rentACar.business.dtos.requests.customer;

import com.etiya.rentACar.business.messages.CustomerMessages;
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

    @NotBlank(message = CustomerMessages.fullNameCannotBeEmpty)
    private String fullName;

    @NotBlank(message = CustomerMessages.emailCannotBeEmpty)
    private String email;

    @NotBlank(message = CustomerMessages.phoneCannotBeEmpty)
    private String phone;

    @NotBlank(message = CustomerMessages.nationalNoCannotBeEmpty)
    private String nationalNo;

    @NotNull(message = CustomerMessages.birthDateCannotBeEmpty)
    private LocalDate birthDate;
}
