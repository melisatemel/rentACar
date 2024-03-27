package com.etiya.rentACar.business.dtos.responses.fuel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class UpdatedFuelResponse {
    private int id;
    private String name;
    private LocalDateTime updatedDate;
}
