package com.etiya.rentACar.business.dtos.requests.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {

        private int id;

        private String startDate;

        private String endDate;

        private String returnDate;

        private double startKilometer;

        private double endKilometer;

        private int carId;

        private int customerId;
}
