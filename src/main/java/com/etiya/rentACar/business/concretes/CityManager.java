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

import java.time.LocalDateTime;
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
        City existCity = cityRepository.findById(updateCityRequest.getId()).get();
        City updatedCity = modelMapperService.forRequest().map(updateCityRequest,City.class);

        updatedCity.setCreatedDate(existCity.getCreatedDate());
        updatedCity = cityRepository.save(updatedCity);

        return modelMapperService.forResponse().map(updatedCity,UpdatedCityResponse.class);
    }

    @Override
    public List<GetCityListResponse> getAll() {
        List<City> cities = cityRepository.findAll();

        return cities.stream()
                .filter(city -> city.getDeletedDate() == null)
                .map(city -> modelMapperService.forResponse()
                        .map(city,GetCityListResponse.class)).collect(Collectors.toList());
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
        City deletedCity = cityRepository.findById(id).get();
        deletedCity.setDeletedDate(LocalDateTime.now());
        cityRepository.save(deletedCity);
    }

    @Override
    public void cityIdMustBeExists(int id) {
        cityBusinessRules.isCityAvailable(id);
    }
}
