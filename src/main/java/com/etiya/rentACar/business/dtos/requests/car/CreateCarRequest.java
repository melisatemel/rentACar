package com.etiya.rentACar.business.dtos.requests.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Validasyonlar sanırım burada
@Data                   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
public class CreateCarRequest {

    @NotNull(message = "Model Year cannot be null!")    //boş yada null olamaz
    private int modelYear;

    @NotBlank(message = "Plate cannot be empty!")
    private String plate;

    private double kilometer;

    @NotNull(message = "Status cannot be empty!")
    private int status;

    @NotNull(message = "Model Year cannot be empty!")
    private double dailyPrice;

    @NotNull(message = "Model Year cannot be empty!")
    private int modelId;

    @NotNull(message = "Rental Branch ID cannot be empty!")
    private int rentalBranchId;
}
