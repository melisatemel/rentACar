package com.etiya.rentACar.business.dtos.responses.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetInvoiceListResponse {
        private int id;
        private double totalPrice;
        private int rentalId;


}
