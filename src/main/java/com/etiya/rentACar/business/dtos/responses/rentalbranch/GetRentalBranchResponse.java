package com.etiya.rentACar.business.dtos.responses.rentalbranch;

import com.etiya.rentACar.business.dtos.responses.city.GetCityResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalBranchResponse {
    private int id;

    private String name;

    private GetCityResponse city;
}
