package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.ModelService;
import com.etiya.rentACar.business.abstracts.RentalBranchService;
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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;
    private ModelService modelService;
    private RentalBranchService rentalBranchService;

    @Override
    public CreatedCarResponse add(CreateCarRequest createCarRequest) {
        rentalBranchService.rentalBranchIdMustBeExists(createCarRequest.getRentalBranchId());
        modelService.modelIdMustBeExists(createCarRequest.getModelId());
        carBusinessRules.carPlateCannotBeDuplicated(createCarRequest.getPlate());
        carBusinessRules.carModelYearCheck(createCarRequest.getModelYear());
        carBusinessRules.carDailyPriceCheck(createCarRequest.getDailyPrice());

        Car car = this.modelMapperService.forRequest().map(createCarRequest,Car.class);
        car.setId(0);
        Car createdCar = carRepository.save(car);

        return this.modelMapperService.forResponse().map(createdCar,CreatedCarResponse.class);

    }

    @Override
    public UpdatedCarResponse update(UpdateCarRequest updateCarRequest) {
        rentalBranchService.rentalBranchIdMustBeExists(updateCarRequest.getRentalBranchId());
        modelService.modelIdMustBeExists(updateCarRequest.getModelId());
        carBusinessRules.carIdMustBeExists(updateCarRequest.getId());
        carBusinessRules.carPlateCannotBeDuplicated(updateCarRequest.getPlate());
        carBusinessRules.carModelYearCheck(updateCarRequest.getModelYear());
        carBusinessRules.carDailyPriceCheck(updateCarRequest.getDailyPrice());

        Car existingCar = carRepository.findById(updateCarRequest.getId()).get();

        this.modelMapperService.forRequest().map(updateCarRequest,existingCar);
        Car updatedCar = carRepository.save(existingCar);


        return modelMapperService.forResponse().map(updatedCar,UpdatedCarResponse.class);

    }

    @Override
    public GetCarResponse getById(int id) {
        carBusinessRules.carIdMustBeExists(id);
        Car car = carRepository.findById(id).get();
        return this.modelMapperService.forResponse().map(car,GetCarResponse.class);
    }

    @Override
    public List<GetCarListResponse> getAll() {
        List<Car> cars = carRepository.findAll();

        return cars.stream().map(car ->
                this.modelMapperService.forResponse().map(car,GetCarListResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void carIdMustBeExists(int id) {
        carBusinessRules.carIdMustBeExists(id);
    }

    @Override
    public Car carStatusUpdate(int id, int status) {
        Car car = carRepository.findById(id).get();
        car.setStatus(status);
        return carRepository.save(car);
    }

    @Override
    public boolean carStatusIsAvailable(int id) {
        Car car = carRepository.findById(id).get();
        return car.getStatus() == 1;
    }

    @Override
    public void delete(int id) {

        carBusinessRules.carIdMustBeExists(id);
        carRepository.deleteById(id);

    }
}
