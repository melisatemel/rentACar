package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.dtos.requests.car.CreateCarRequest;
import com.etiya.rentACar.business.dtos.requests.car.UpdateCarRequest;
import com.etiya.rentACar.business.dtos.responses.car.CreatedCarResponse;
import com.etiya.rentACar.business.dtos.responses.car.GetCarListResponse;
import com.etiya.rentACar.business.dtos.responses.car.GetCarResponse;
import com.etiya.rentACar.business.dtos.responses.car.UpdatedCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Restful yapısın demek yani erişim noktası
@AllArgsConstructor
@RequestMapping("api/v1/cars") //localhost:8081/api/v1/brands Adresleme=erişim noktasıyla controller arası iletişimi kurar
public class CarsController {
    private CarService carService;

    @GetMapping
    public List<GetCarListResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable int id) {
        return carService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCarResponse add(@Valid  @RequestBody CreateCarRequest createCarRequest) {
        return carService.add(createCarRequest);
    }

    @PutMapping
    public UpdatedCarResponse update(@Valid@RequestBody UpdateCarRequest updateCarRequest) {
        return carService.update(updateCarRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        carService.delete(id);
    }
}
