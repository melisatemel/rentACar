package com.etiya.rentACar.business.dtos.responses.car;

import com.etiya.rentACar.business.dtos.responses.city.GetCityResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelResponse;
import com.etiya.rentACar.business.dtos.responses.rentalbranch.GetRentalBranchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class GetCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double kilometer;
    private int state;
    private double dailyPrice;
    private int modelId;
    private int rentalBranchId;
}
