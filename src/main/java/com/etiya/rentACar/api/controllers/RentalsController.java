package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.dtos.requests.rental.CreateRentalRequest;
import com.etiya.rentACar.business.dtos.requests.rental.UpdateRentalRequest;
import com.etiya.rentACar.business.dtos.responses.rental.GetRentalListResponse;
import com.etiya.rentACar.business.dtos.responses.rental.GetRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rentals")
public class RentalsController {

    private RentalService rentalService;

    @GetMapping
    public List<GetRentalListResponse> getAll(){
        return rentalService.getAll();
    }

    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable int id){
        return rentalService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateRentalRequest createRentalRequest){
        rentalService.add(createRentalRequest);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest){
        rentalService.update(updateRentalRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        rentalService.delete(id);
    }
}
