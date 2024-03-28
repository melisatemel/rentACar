package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.fuel.CreateFuelRequest;
import com.etiya.rentACar.business.dtos.requests.fuel.UpdateFuelRequest;
import com.etiya.rentACar.business.dtos.responses.fuel.CreatedFuelResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.GetFuelListResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.GetFuelResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.UpdatedFuelResponse;
import com.etiya.rentACar.entities.Fuel;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);
    UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest);
    GetFuelResponse getById(int id);

    List<GetFuelListResponse> getAll();

    void fuelIdMustBeExists(int id);
    void delete(int id);
}
