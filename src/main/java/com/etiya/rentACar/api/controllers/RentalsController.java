package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.dtos.requests.rental.CreateRentalRequest;
import com.etiya.rentACar.business.dtos.requests.rental.ReturnRentalRequest;
import com.etiya.rentACar.business.dtos.requests.rental.UpdateRentalRequest;
import com.etiya.rentACar.business.dtos.responses.rental.CreatedRentalResponse;
import com.etiya.rentACar.business.dtos.responses.rental.GetRentalListResponse;
import com.etiya.rentACar.business.dtos.responses.rental.GetRentalResponse;
import com.etiya.rentACar.business.dtos.responses.rental.UpdatedRentalResponse;
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
    public CreatedRentalResponse add(@RequestBody @Valid CreateRentalRequest createRentalRequest){
       return  rentalService.add(createRentalRequest);
    }

    @PostMapping("/return")
    public String returnCar(@RequestBody @Valid ReturnRentalRequest returnRentalRequest){
          return rentalService.returnCar(returnRentalRequest);
    }

    @PutMapping
    public UpdatedRentalResponse update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest){
       return rentalService.update(updateRentalRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        rentalService.delete(id);
    }
}
