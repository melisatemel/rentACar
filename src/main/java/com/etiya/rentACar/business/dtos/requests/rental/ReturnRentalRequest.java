package com.etiya.rentACar.business.dtos.requests.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnRentalRequest {

        private int id;
        private LocalDate returnDate;
        private int returnBranchId;
        private double endKilometer;
}
