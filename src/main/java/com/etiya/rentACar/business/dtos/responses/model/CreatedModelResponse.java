package com.etiya.rentACar.business.dtos.responses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class CreatedModelResponse {
    private int id;

    private String name;

    private int brandId;

    private int fuelId;

    private int transmissionId;

    private LocalDateTime createdDate;
}
