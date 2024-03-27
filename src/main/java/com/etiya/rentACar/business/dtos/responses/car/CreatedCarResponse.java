package com.etiya.rentACar.business.dtos.responses.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class CreatedCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private int status;
    private double dailyPrice;
    private int modelId;
    private LocalDateTime createdDate;
}
