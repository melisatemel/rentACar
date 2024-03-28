package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.dtos.requests.rental.CreateRentalRequest;
import com.etiya.rentACar.business.dtos.requests.rental.UpdateRentalRequest;
import com.etiya.rentACar.business.dtos.responses.rental.CreatedRentalResponse;
import com.etiya.rentACar.business.dtos.responses.rental.GetRentalListResponse;
import com.etiya.rentACar.business.dtos.responses.rental.GetRentalResponse;
import com.etiya.rentACar.business.dtos.responses.rental.UpdatedRentalResponse;
import com.etiya.rentACar.business.rules.RentalBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.RentalRepository;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final RentalBusinessRules rentalBusinessRules;

    @Override
    public CreatedRentalResponse add(CreateRentalRequest createRentalRequest) {
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rentalRepository.save(rental);
        return modelMapperService.forResponse().map(rental, CreatedRentalResponse.class);

    }

    @Override
    public UpdatedRentalResponse update(UpdateRentalRequest updateRentalRequest) {
        rentalBusinessRules.rentalIdMustBeExist(updateRentalRequest.getId());

        Rental existingRental = rentalRepository.findById(updateRentalRequest.getId()).orElseThrow(() -> new RuntimeException("Rental not found"));

        modelMapperService.forRequest().map(updateRentalRequest, existingRental);

        Rental updatedRental = rentalRepository.save(existingRental);

        return modelMapperService.forResponse().map(updatedRental, UpdatedRentalResponse.class);
    }

    @Override
    public List<GetRentalListResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(rental -> this.modelMapperService.forResponse()
                        .map(rental, GetRentalListResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetRentalResponse getById(int id) {
        Car car = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found")).getCar();
        return modelMapperService.forResponse().map(car, GetRentalResponse.class);
    }

    @Override
    public void delete(int id) {
        rentalBusinessRules.rentalIdMustBeExist(id);
        rentalRepository.deleteById(id);
    }
}
