package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.FuelService;
import com.etiya.rentACar.business.dtos.requests.fuel.CreateFuelRequest;
import com.etiya.rentACar.business.dtos.requests.fuel.UpdateFuelRequest;
import com.etiya.rentACar.business.dtos.responses.fuel.CreatedFuelResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.GetFuelListResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.GetFuelResponse;
import com.etiya.rentACar.business.dtos.responses.fuel.UpdatedFuelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Restful yapısın demek yani erişim noktası
@AllArgsConstructor
@RequestMapping("api/v1/fuels") //localhost:8081/api/v1/brands Adresleme=erişim noktasıyla controller arası iletişimi kurar
public class FuelsController {
    private FuelService fuelService;

    @GetMapping
    public List<GetFuelListResponse> getAll(){
        return fuelService.getAll();
    }

    @GetMapping("/{id}")
    public GetFuelResponse getById(@PathVariable int id){
        return fuelService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse add(@Valid @RequestBody CreateFuelRequest createFuelRequest){   //RequestBody yapılmadığında postmande hata verebilir
        return fuelService.add(createFuelRequest);
    }

    @PutMapping
    public UpdatedFuelResponse update(@Valid @RequestBody UpdateFuelRequest updateFuelRequest){
        return fuelService.update(updateFuelRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        fuelService.delete(id);
    }
}
