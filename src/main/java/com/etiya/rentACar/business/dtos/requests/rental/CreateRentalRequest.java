package com.etiya.rentACar.business.dtos.requests.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    private String startDate;

    private String endDate;

    private double startKilometer;

    private double endKilometer;

    private int carId;

    private int customerId;
}
