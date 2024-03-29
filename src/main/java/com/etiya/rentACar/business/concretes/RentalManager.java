package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.CustomerService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.dtos.requests.rental.CreateRentalRequest;
import com.etiya.rentACar.business.dtos.requests.rental.ReturnRentalRequest;
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

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final RentalBusinessRules rentalBusinessRules;
    private final CarService carService;
    private final CustomerService customerService;

    @Override
    public CreatedRentalResponse add(CreateRentalRequest createRentalRequest) {

        carService.carIdMustBeExists(createRentalRequest.getCarId());
        customerService.customerIdMustBeExists(createRentalRequest.getCustomerId());
        rentalBusinessRules.carStatusMustBeTrue(createRentalRequest.getCarId());
        rentalBusinessRules.checkIfCustomerHasDeliveredRentalCar(createRentalRequest.getCustomerId());

        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

        rental.setStartKilometer(carService.carStatusUpdate(createRentalRequest.getCarId(), 2).getKilometer());
        rental.setSubTotalPrice(carService.getCarById(createRentalRequest.getCarId()).getDailyPrice() * ChronoUnit.DAYS.between(createRentalRequest.getStartDate(), createRentalRequest.getEndDate()));

        Rental createdRental = rentalRepository.save(rental);

        return modelMapperService.forResponse().map(createdRental, CreatedRentalResponse.class);

    }

    @Override
    public UpdatedRentalResponse update(UpdateRentalRequest updateRentalRequest) {
        rentalBusinessRules.rentalIdMustBeExist(updateRentalRequest.getId());
        carService.carIdMustBeExists(updateRentalRequest.getCarId());
        customerService.customerIdMustBeExists(updateRentalRequest.getCustomerId());
        Rental existingRental = rentalRepository.findById(updateRentalRequest.getId()).get();



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
        rentalBusinessRules.rentalIdMustBeExist(id);
        Rental rental = rentalRepository.findById(id).get();
        return modelMapperService.forResponse().map(rental, GetRentalResponse.class);
    }

    @Override
    public String returnCar(ReturnRentalRequest returnRentalRequest) {
        rentalBusinessRules.rentalIdMustBeExist(returnRentalRequest.getId());
        Rental rental = rentalRepository.findById(returnRentalRequest.getId()).get();
        rental.setReturnDate(returnRentalRequest.getReturnDate());
        rental.setEndKilometer(returnRentalRequest.getEndKilometer());

        rentalRepository.save(rental);
        carService.carStatusUpdate(rental.getCar().getId(), 1);
        carService.carKilometerUpdate(rental.getCar().getId(), returnRentalRequest.getEndKilometer());
        carService.carBranchUpdate(rental.getCar().getId(), returnRentalRequest.getReturnBranchId());
        return "Car returned successfully";
    }

    @Override
    public void delete(int id) {
        rentalBusinessRules.rentalIdMustBeExist(id);
        rentalRepository.deleteById(id);
    }
}
