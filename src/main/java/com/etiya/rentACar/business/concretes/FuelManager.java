package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.FuelService;
import com.etiya.rentACar.business.dtos.requests.fuel.CreateFuelRequest;
import com.etiya.rentACar.business.dtos.requests.fuel.UpdateFuelRequest;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.CreatedFuelResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.GetFuelListResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.GetFuelResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.UpdatedFuelResponse;
import com.etiya.rentACar.business.rules.FuelBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.FuelRepository;
import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor     //Parametreli constructor   oluşturur
@Service    //Bu sınıf bir business nesnesidir. IoC de oluşturulacak.   servisten implemente edildiğinden
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;
    private final ModelMapperService modelMapperService;
    private FuelBusinessRules fuelBusinessRules;

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest) {
        fuelBusinessRules.fuelNameCannotBeDuplicated(createFuelRequest.getName());

        Fuel fuel = this.modelMapperService.forRequest().map(createFuelRequest, Fuel.class);

        Fuel savedFuel =  fuelRepository.save(fuel);

        return this.modelMapperService.forResponse().map(savedFuel, CreatedFuelResponse.class);
    }

    @Override
    public UpdatedFuelResponse update(UpdateFuelRequest updateFuelRequest) {
        fuelBusinessRules.fuelIdMustBeExists(updateFuelRequest.getId());
        fuelBusinessRules.fuelNameCannotBeDuplicated(updateFuelRequest.getName());

        Fuel fuel = fuelRepository.findById(updateFuelRequest.getId()).get();

        this.modelMapperService.forRequest().map(updateFuelRequest, fuel);
        Fuel updatedFuel = fuelRepository.save(fuel);

        return modelMapperService.forResponse().map(updatedFuel,UpdatedFuelResponse.class);
    }

    @Override
    public GetFuelResponse getById(int id) {
        fuelBusinessRules.fuelIdMustBeExists(id);
        Fuel fuel = fuelRepository.findById(id).get();

        return this.modelMapperService.forResponse().map(fuel, GetFuelResponse.class);
    }

    @Override
    public List<GetFuelListResponse> getAll() {
        List<Fuel> fuels = fuelRepository.findAll();
        List<GetFuelListResponse> responses = fuels.stream().map(fuel ->
                this.modelMapperService.forResponse().map(fuel,GetFuelListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public void fuelIdMustBeExists(int id) {
        fuelBusinessRules.fuelIdMustBeExists(id);
    }


    @Override
    public void delete(int id) {
        fuelBusinessRules.fuelIdMustBeExists(id);
        fuelRepository.deleteById(id);
    }
}
