package com.etiya.rentACar.business.dtos.responses.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetInvoiceResponse {
        private int id;
        private double totalPrice;
        private int rentalId;

}
