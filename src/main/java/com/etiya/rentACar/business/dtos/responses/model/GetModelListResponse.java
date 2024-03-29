package com.etiya.rentACar.business.dtos.responses.model;

import com.etiya.rentACar.business.dtos.responses.brand.GetBrandResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.GetFuelResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.GetTransmissionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class GetModelListResponse {
    private int id;

    private String name;

    private GetBrandResponse brand;

    private GetFuelResponse fuel;

    private GetTransmissionResponse transmission;

}
