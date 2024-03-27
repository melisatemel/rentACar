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
        transmissionRepository.save(transmission);

        CreatedTransmissionResponse createdTransmissionResponse = this.modelMapperService.forResponse().map(transmission,CreatedTransmissionResponse.class);    //veri eklenme işlemi başarılı olduysa burası döner.
        return createdTransmissionResponse;
    }

    @Override
    public UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest) {
        transmissionBusinessRules.transmissionNameCannotBeDuplicated(updateTransmissionRequest.getName());

        Transmission transmission = transmissionRepository.findById(updateTransmissionRequest.getId()).orElseThrow(() -> new IllegalArgumentException("Transmission not found"));
        this.modelMapperService.forRequest().map(updateTransmissionRequest,transmission);

        transmissionRepository.save(transmission);

        UpdatedTransmissionResponse updatedTransmissionResponse = modelMapperService.forResponse().map(transmission, UpdatedTransmissionResponse.class);

        return updatedTransmissionResponse;
    }

    @Override
    public List<GetTransmissionListResponse> getAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();
        List<GetTransmissionListResponse> getTransmissionListResponses = transmissions.stream().map(transmission ->
                this.modelMapperService.forResponse().map(transmission,GetTransmissionListResponse.class)).collect(Collectors.toList());

        return getTransmissionListResponses;
    }

    @Override
    public GetTransmissionResponse getById(int id) {
        Transmission transmission = transmissionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transmission not found"));
        GetTransmissionResponse getTransmissionResponse = this.modelMapperService.forResponse().map(transmission,GetTransmissionResponse.class);
        return getTransmissionResponse;
    }


    @Override
    public void delete(int id) {
        transmissionRepository.deleteById(id);
    }
}
