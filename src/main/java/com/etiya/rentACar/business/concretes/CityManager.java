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
        City savedCity = cityRepository.save(city);

        return modelMapperService.forResponse().map(savedCity,CreatedCityResponse.class);
    }

    @Override
    public UpdatedCityResponse update(UpdateCityRequest updateCityRequest) {
        cityBusinessRules.cityIdMustBeExists(updateCityRequest.getId());
        cityBusinessRules.cityNAmeCannotBeDuplicated(updateCityRequest.getName());
        City cityToUpdate = cityRepository.findById(updateCityRequest.getId()).get();

        modelMapperService.forRequest().map(updateCityRequest,cityToUpdate);
        //todo: check createdDate
        City updatedCity = cityRepository.save(cityToUpdate);

        return modelMapperService.forResponse().map(updatedCity,UpdatedCityResponse.class);
    }

    @Override
    public List<GetCityListResponse> getAll() {
        List<City> cities = cityRepository.findAll();
        List<GetCityListResponse> responses = cities.stream().map(city -> modelMapperService.forResponse()
                .map(city, GetCityListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetCityResponse getById(int id) {
        cityBusinessRules.cityIdMustBeExists(id);
        City city = cityRepository.findById(id).get();

        return modelMapperService.forResponse().map(city,GetCityResponse.class);
    }

    @Override
    public void delete(int id) {
        cityBusinessRules.cityIdMustBeExists(id);
        cityRepository.deleteById(id);
    }

    @Override
    public void isCityAvailable(int id) {
        cityBusinessRules.isCityAvailable(id);
    }
}
