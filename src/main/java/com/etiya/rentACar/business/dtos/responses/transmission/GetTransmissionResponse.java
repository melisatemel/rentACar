package com.etiya.rentACar.business.dtos.responses.transmission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class GetTransmissionResponse {
    private int id;

    private String name;

}
