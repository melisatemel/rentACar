package com.etiya.rentACar.business.dtos.responses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class GetModelListResponse {
    private int id;

    private String name;

    private String brandName;

    private String fuelName;

    private String transmissionName;

}
