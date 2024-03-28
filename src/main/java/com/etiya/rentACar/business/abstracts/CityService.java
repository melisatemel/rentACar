package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.city.CreateCityRequest;
import com.etiya.rentACar.business.dtos.requests.city.UpdateCityRequest;
import com.etiya.rentACar.business.dtos.responses.city.*;


import java.util.List;

public interface CityService {
    CreatedCityResponse add(CreateCityRequest createCityRequest);
    UpdatedCityResponse update(UpdateCityRequest updateCityRequest);
    List<GetCityListResponse> getAll();
    GetCityResponse getById(int id);
    void delete(int id);
    void cityIdMustBeExists(int id);
}
