package com.etiya.rentACar.business.dtos.requests.brand;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur

public class UpdateBrandRequest {
    @NotNull               //Sen bunu boş geçiremezsin. Boş olursa hata verir  !Update yaparken id olmalı!
    private int id;

    @NotNull                //Sen bunu boş geçiremezsin. Boş olursa hata verir
    @Size(min=2, max = 30)  //kural belirtildi
    private String name;    //entitieslerdeki brand özelliiklerinden ismi yazdık
}
