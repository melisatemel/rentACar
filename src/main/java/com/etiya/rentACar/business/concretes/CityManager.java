package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.dtos.requests.city.CreateCityRequest;
import com.etiya.rentACar.business.dtos.requests.city.UpdateCityRequest;
import com.etiya.rentACar.business.dtos.responses.city.*;
import com.etiya.rentACar.business.rules.CityBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.CityRepository;
import com.etiya.rentACar.entities.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CityManager implements CityService {
    private CityRepository cityRepository;
    private ModelMapperService modelMapperService;
    private CityBusinessRules cityBusinessRules;


    @Override
    public CreatedCityResponse add(CreateCityRequest createCityRequest) {
        cityBusinessRules.cityNAmeCannotBeDuplicated(createCityRequest.getName());

        City city = modelMapperService.forRequest().map(createCityRequest,City.class);
        cityRepository.save(city);

        CreatedCityResponse createdCityResponse = modelMapperService.forResponse().map(city,CreatedCityResponse.class);
        return createdCityResponse;
    }

    @Override
    public UpdatedCityResponse update(UpdateCityRequest updateCityRequest) {
        cityBusinessRules.cityNAmeCannotBeDuplicated(updateCityRequest.getName());
        City cityToUpdate = cityRepository.findById(updateCityRequest.getId()).orElseThrow(() ->
                new IllegalArgumentException("City not found!"));

        modelMapperService.forRequest().map(updateCityRequest,cityToUpdate);
        cityRepository.save(cityToUpdate);
        UpdatedCityResponse updatedCityResponse = modelMapperService.forResponse().map(cityToUpdate,UpdatedCityResponse.class);
        return updatedCityResponse;
    }

    @Override
    public List<GetCityListResponse> getAll() {
        List<City> cities = cityRepository.findAll();
        List<GetCityListResponse> cityListResponses = cities.stream().map(city -> modelMapperService.forResponse()
                .map(city, GetCityListResponse.class)).collect(Collectors.toList());
        return cityListResponses;
    }

    @Override
    public GetCityResponse getById(int id) {
        City city = cityRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("City not found"));
        GetCityResponse getCityResponse = modelMapperService.forResponse().map(city,GetCityResponse.class);
        return getCityResponse;
    }

    @Override
    public void delete(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void isCityAvailable(int id) {
        cityBusinessRules.isCityAvailable(id);
    }
}
