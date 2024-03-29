package com.etiya.rentACar.business.dtos.responses.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class UpdatedCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double kilometer;
    private int state;
    private double dailyPrice;
    private int modelId;
    private int rentalBranchId;
    private LocalDateTime updatedDate;
}
