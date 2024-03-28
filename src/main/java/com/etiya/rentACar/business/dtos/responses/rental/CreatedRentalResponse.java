package com.etiya.rentACar.business.dtos.responses.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedRentalResponse {

    private int id;

    private String startDate;

    private String endDate;

    private String returnDate;

    private double startKilometer;

    private double endKilometer;

    private int carId;

    private int customerId;

    private LocalDateTime createdDate;

}
