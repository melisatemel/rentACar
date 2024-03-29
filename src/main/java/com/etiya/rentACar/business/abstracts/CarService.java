package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.car.CreateCarRequest;
import com.etiya.rentACar.business.dtos.requests.car.UpdateCarRequest;
import com.etiya.rentACar.business.dtos.responses.car.CreatedCarResponse;
import com.etiya.rentACar.business.dtos.responses.car.GetCarListResponse;
import com.etiya.rentACar.business.dtos.responses.car.GetCarResponse;
import com.etiya.rentACar.business.dtos.responses.car.UpdatedCarResponse;
import com.etiya.rentACar.entities.Car;

import java.util.List;

public interface CarService {
    CreatedCarResponse add(CreateCarRequest createCarRequest);
    UpdatedCarResponse update(UpdateCarRequest updateCarRequest);

    GetCarResponse getById(int id);

    List<GetCarListResponse> getAll();

    void carIdMustBeExists(int id);
    Car carStatusUpdate(int id, int status);

    Car getCarById(int id);
    boolean carStatusIsAvailable(int id);
    void delete(int id);

    void carKilometerUpdate(int id, double endKilometer);

    void carBranchUpdate(int id, int returnBranchId);
}
