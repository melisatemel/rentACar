package com.etiya.rentACar.business.dtos.responses.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedInvoiceResponse {
        private int id;
        private double totalPrice;
        private int rentalId;
        private LocalDateTime createdDate;
}
