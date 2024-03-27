package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.ModelService;
import com.etiya.rentACar.business.dtos.requests.car.CreateCarRequest;
import com.etiya.rentACar.business.dtos.requests.car.UpdateCarRequest;
import com.etiya.rentACar.business.dtos.responses.car.CreatedCarResponse;
import com.etiya.rentACar.business.dtos.responses.car.GetCarListResponse;
import com.etiya.rentACar.business.dtos.responses.car.GetCarResponse;
import com.etiya.rentACar.business.dtos.responses.car.UpdatedCarResponse;
import com.etiya.rentACar.business.rules.CarBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.CarRepository;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service    //Bu sınıf bir business nesnesidir. IoC de oluşturulacak.
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;

    @Override
    public CreatedCarResponse add(CreateCarRequest createCarRequest) {
        carBusinessRules.carPlateCannotBeDuplicated(createCarRequest.getPlate());
        carBusinessRules.carModelYearCheck(createCarRequest.getModelYear());
        carBusinessRules.carDailyPriceCheck(createCarRequest.getDailyPrice());

        Car car = this.modelMapperService.forRequest().map(createCarRequest,Car.class);
        carRepository.save(car);
        CreatedCarResponse createdCarResponse = this.modelMapperService.forResponse().map(car,CreatedCarResponse.class);
        car.setCreatedDate(LocalDateTime.now());
        return createdCarResponse;

    }

    @Override
    public UpdatedCarResponse update(UpdateCarRequest updateCarRequest) {
        carBusinessRules.carPlateCannotBeDuplicated(updateCarRequest.getPlate());
        carBusinessRules.carModelYearCheck(updateCarRequest.getModelYear());
        carBusinessRules.carDailyPriceCheck(updateCarRequest.getDailyPrice());

        Car car = carRepository.findById(updateCarRequest.getId()).orElseThrow(() -> new IllegalArgumentException("Car not found"));


        //modelmapperla tekrar yap
        car.setName(updateCarRequest.getName());
        car.setStatus(updateCarRequest.getStatus());
        car.setDailyPrice(updateCarRequest.getDailyPrice());
        car.setPlate(updateCarRequest.getPlate());
        car.setModelYear(updateCarRequest.getModelYear());

        Model model = new Model();
        model.setId(updateCarRequest.getModelId());
        car.setModel(model);

        carRepository.save(car);
        //modelmapper
        UpdatedCarResponse updatedCarResponse = modelMapperService.forResponse().map(car,UpdatedCarResponse.class);

        return updatedCarResponse;

    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car not found"));//brand bulamazsan hata fırlat
        GetCarResponse getCarResponse = this.modelMapperService.forResponse().map(car,GetCarResponse.class);
        return getCarResponse;
    }

    @Override
    public List<GetCarListResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetCarListResponse> carListResponses = cars.stream().map(car ->
                this.modelMapperService.forResponse().map(car,GetCarListResponse.class)).collect(Collectors.toList());
        return carListResponses;
    }

    @Override
    public void delete(int id) {

        carRepository.deleteById(id);

    }
}
