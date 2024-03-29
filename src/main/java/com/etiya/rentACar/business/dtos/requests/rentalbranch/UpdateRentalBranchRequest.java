package com.etiya.rentACar.business.dtos.requests.rentalbranch;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalBranchRequest {
    @NotNull
    private int id;
    @NotNull
    private int cityId;

    private String name;
}
