package com.etiya.rentACar.business.dtos.responses.rental;

import com.etiya.rentACar.business.dtos.responses.car.GetCarResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerResponse;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedRentalResponse {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private double startKilometer;
    private double subTotalPrice;

    private int carId;

    private int customerId;

    private LocalDateTime createdDate;

}
