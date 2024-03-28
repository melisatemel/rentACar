package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.transmission.CreateTransmissionRequest;
import com.etiya.rentACar.business.dtos.requests.transmission.UpdateTransmissionRequest;
import com.etiya.rentACar.business.dtos.responses.transmission.CreatedTransmissionResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.GetTransmissionListResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.GetTransmissionResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.UpdatedTransmissionResponse;
import com.etiya.rentACar.entities.Transmission;

import java.util.List;

public interface TransmissionService {
    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);

    UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest);

    List<GetTransmissionListResponse> getAll();

    GetTransmissionResponse getById(int id);

    void transmissionIdMustBeExists(int id);
    void delete(int id);
}
