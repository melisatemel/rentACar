package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.TransmissionService;
import com.etiya.rentACar.business.dtos.requests.transmission.CreateTransmissionRequest;
import com.etiya.rentACar.business.dtos.requests.transmission.UpdateTransmissionRequest;
import com.etiya.rentACar.business.dtos.responses.transmission.CreatedTransmissionResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.GetTransmissionListResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.GetTransmissionResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.UpdatedTransmissionResponse;
import com.etiya.rentACar.business.rules.TransmissionBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.TransmissionRepository;
import com.etiya.rentACar.entities.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service    //Bu sınıf bir business nesnesidir. IoC de oluşturulacak.
public class TransmissionManager implements TransmissionService {

    private TransmissionRepository transmissionRepository;
    private final ModelMapperService modelMapperService;
    private TransmissionBusinessRules transmissionBusinessRules;

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        transmissionBusinessRules.transmissionNameCannotBeDuplicated(createTransmissionRequest.getName());

        Transmission transmission = this.modelMapperService.forRequest().map(createTransmissionRequest,Transmission.class); //set ve get işlemlerini map()'in içerisinde yapıyoruz.
        Transmission savedTransmission = transmissionRepository.save(transmission);

        return this.modelMapperService.forResponse().map(savedTransmission,CreatedTransmissionResponse.class);
    }

    @Override
    public UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest) {
        transmissionBusinessRules.transmissionIdMustBeExists(updateTransmissionRequest.getId());
        transmissionBusinessRules.transmissionNameCannotBeDuplicated(updateTransmissionRequest.getName());

        Transmission transmission = transmissionRepository.findById(updateTransmissionRequest.getId()).orElseThrow(() -> new IllegalArgumentException("Transmission not found"));
        this.modelMapperService.forRequest().map(updateTransmissionRequest,transmission);

        Transmission updatedTransmission =  transmissionRepository.save(transmission);

        return modelMapperService.forResponse().map(updatedTransmission, UpdatedTransmissionResponse.class);
    }

    @Override
    public List<GetTransmissionListResponse> getAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();

        return transmissions.stream().map(transmission ->
                this.modelMapperService.forResponse().map(transmission,GetTransmissionListResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetTransmissionResponse getById(int id) {
        transmissionBusinessRules.transmissionIdMustBeExists(id);
        Transmission transmission = transmissionRepository.findById(id).get();

        return this.modelMapperService.forResponse().map(transmission,GetTransmissionResponse.class);
    }

    @Override
    public void transmissionIdMustBeExists(int id) {
        transmissionBusinessRules.transmissionIdMustBeExists(id);
    }


    @Override
    public void delete(int id) {
        transmissionBusinessRules.transmissionIdMustBeExists(id);
        transmissionRepository.deleteById(id);
    }
}
