package com.etiya.rentACar.business.dtos.responses.rental;

import com.etiya.rentACar.business.dtos.responses.car.GetCarResponse;
import com.etiya.rentACar.business.dtos.responses.customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalListResponse {

        private int id;

        private LocalDate startDate;

        private LocalDate endDate;

        private LocalDate returnDate;
        private double subTotalPrice;

        private double startKilometer;

        private double endKilometer;
        private int carId;

        private int customerId;


}
