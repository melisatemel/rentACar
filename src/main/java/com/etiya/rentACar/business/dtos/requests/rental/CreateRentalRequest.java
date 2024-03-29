package com.etiya.rentACar.business.dtos.requests.rental;

import com.etiya.rentACar.business.dtos.responses.car.GetCarResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    private LocalDate startDate;

    private LocalDate endDate;

    private int carId;

    private int customerId;
}
