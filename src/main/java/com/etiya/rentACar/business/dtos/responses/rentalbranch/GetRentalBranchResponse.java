package com.etiya.rentACar.business.dtos.responses.rentalbranch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalBranchResponse {
    private int id;
    private int cityId;
}
