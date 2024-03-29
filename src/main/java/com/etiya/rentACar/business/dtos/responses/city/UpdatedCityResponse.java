package com.etiya.rentACar.business.dtos.responses.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedCityResponse {
    private int id;
    private String name;
    private LocalDateTime updatedDate;
}
