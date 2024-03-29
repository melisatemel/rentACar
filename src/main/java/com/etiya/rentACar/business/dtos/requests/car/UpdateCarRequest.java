package com.etiya.rentACar.business.dtos.requests.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class UpdateCarRequest {
    private int id;
    private int modelYear;
    private String plate;
    private double kilometer;
    private int status;
    private double dailyPrice;
    private int modelId;
    private int rentalBranchId;
}
