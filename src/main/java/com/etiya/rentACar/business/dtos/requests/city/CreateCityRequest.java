package com.etiya.rentACar.business.dtos.requests.city;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class CreateCityRequest {
    @NotEmpty(message = "City name cannot be empty!")
    @Size(min=2, max = 30)
    private String name;
}
