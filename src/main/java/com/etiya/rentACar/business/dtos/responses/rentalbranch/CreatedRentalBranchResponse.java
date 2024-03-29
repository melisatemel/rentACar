package com.etiya.rentACar.business.dtos.responses.rentalbranch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedRentalBranchResponse {
    private int id;
    private int cityId;
    private String name;
    private LocalDateTime createdDate;
}
