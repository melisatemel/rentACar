package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.rental.CreateRentalRequest;
import com.etiya.rentACar.business.dtos.requests.rental.ReturnRentalRequest;
import com.etiya.rentACar.business.dtos.requests.rental.UpdateRentalRequest;
import com.etiya.rentACar.business.dtos.responses.rental.CreatedRentalResponse;
import com.etiya.rentACar.business.dtos.responses.rental.GetRentalListResponse;
import com.etiya.rentACar.business.dtos.responses.rental.GetRentalResponse;
import com.etiya.rentACar.business.dtos.responses.rental.UpdatedRentalResponse;

import java.util.List;

public interface RentalService {

    CreatedRentalResponse add(CreateRentalRequest createRentalRequest);

    UpdatedRentalResponse update(UpdateRentalRequest updateRentalRequest);

    List<GetRentalListResponse> getAll();

    GetRentalResponse getById(int id);

    String returnCar(ReturnRentalRequest returnRentalRequest);
    void delete(int id);


}
