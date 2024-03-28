package com.etiya.rentACar.business.dtos.requests.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
        private double totalPrice;
        private int rentalId;
}
