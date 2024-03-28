package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.dtos.requests.city.*;
import com.etiya.rentACar.business.dtos.responses.city.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Restful yapısın demek yani erişim noktası
@AllArgsConstructor
@RequestMapping("api/v1/cities")
public class CitiesController {

    private CityService cityService;

    @GetMapping //Change access modifier
    public List<GetCityListResponse> getAll(){
        return cityService.getAll();
    }

    @GetMapping("/{id}")    //getById a erişimi sağlar data çekmek için
    public GetCityResponse getById(@PathVariable int id) { //@PathVariable demek git pathten oku
        return cityService.getById(id);
    }

    @PostMapping    //Veri EKLEME
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCityResponse add(@RequestBody @Valid() CreateCityRequest createCityRequest) {
        return cityService.add(createCityRequest);    //createBrandRequest' i, brandService'e gönder
    }

    @PutMapping //Güncelleme
    public UpdatedCityResponse update(@Valid@RequestBody UpdateCityRequest updateCityRequest) {
        return cityService.update(updateCityRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {  //pathten okuyup sil
       cityService.delete(id);
    }
}
