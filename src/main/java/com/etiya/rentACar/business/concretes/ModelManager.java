package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.abstracts.FuelService;
import com.etiya.rentACar.business.abstracts.ModelService;
import com.etiya.rentACar.business.abstracts.TransmissionService;
import com.etiya.rentACar.business.dtos.requests.model.CreateModelRequest;
import com.etiya.rentACar.business.dtos.requests.model.UpdateModelRequest;
import com.etiya.rentACar.business.dtos.responses.fuel.GetFuelListResponse;
import com.etiya.rentACar.business.dtos.responses.model.CreatedModelResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelListResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelResponse;
import com.etiya.rentACar.business.dtos.responses.model.UpdatedModelResponse;
import com.etiya.rentACar.business.rules.ModelBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.ModelRepository;
import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.Fuel;
import com.etiya.rentACar.entities.Model;
import com.etiya.rentACar.entities.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor     //Parametreli constructor   oluşturur
@Service    //Bu sınıf bir business nesnesidir. IoC de oluşturulacak.
public class ModelManager implements ModelService {

    private ModelRepository modelRepository;
    private final ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        modelBusinessRules.modelNameCannotBeDuplicated(createModelRequest.getName());


        Model model = this.modelMapperService.forRequest().map(createModelRequest,Model.class);
        modelRepository.save(model);
        CreatedModelResponse createdModelResponse = this.modelMapperService.forResponse().map(model,CreatedModelResponse.class);

        return createdModelResponse;
    }

    @Override
    public UpdatedModelResponse update(UpdateModelRequest updateModelRequest) {
        modelBusinessRules.modelNameCannotBeDuplicated(updateModelRequest.getName());


        Model model = modelRepository.findById(updateModelRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Model not found"));



//        this.modelMapperService.forRequest().map(updateModelRequest, model);
        model.setName(updateModelRequest.getName());

        Transmission transmission = new Transmission();
        transmission.setId(updateModelRequest.getTransmissionId());
        model.setTransmission(transmission);

        Fuel fuel = new Fuel();
        fuel.setId(updateModelRequest.getFuelId());
        model.setFuel(fuel);

        Brand brand = new Brand();
        brand.setId(updateModelRequest.getBrandId());
        model.setBrand(brand);

        modelRepository.save(model);

        UpdatedModelResponse updatedModelResponse = modelMapperService.forResponse().map(model, UpdatedModelResponse.class);
        return updatedModelResponse;
    }

    @Override
    public List<GetModelListResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> modelListResponses = models.stream() //stream()
                .map(model -> modelMapperService.forResponse().map(model, GetModelListResponse.class))
                   //döngü işlemleri yapılır                //dönüşüm işlemi yapıyor
                .collect(Collectors.toList());

        return modelListResponses;
    }

    @Override
    public GetModelResponse getById(int id) {

        Model model = modelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Model not found"));
        GetModelResponse getModelResponse = this.modelMapperService.forResponse().map(model,GetModelResponse.class);
        return getModelResponse;

    }


    @Override
    public void delete(int id) {
        modelRepository.deleteById(id);
    }
}
