package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.abstracts.FuelService;
import com.etiya.rentACar.business.abstracts.ModelService;
import com.etiya.rentACar.business.abstracts.TransmissionService;
import com.etiya.rentACar.business.dtos.requests.model.CreateModelRequest;
import com.etiya.rentACar.business.dtos.requests.model.UpdateModelRequest;
import com.etiya.rentACar.business.dtos.responses.model.CreatedModelResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelListResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelResponse;
import com.etiya.rentACar.business.dtos.responses.model.UpdatedModelResponse;
import com.etiya.rentACar.business.rules.ModelBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.ModelRepository;
import com.etiya.rentACar.entities.Model;
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
    private BrandService brandService;
    private FuelService fuelService;
    private TransmissionService transmissionService;

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        brandService.brandIdMustBeExists(createModelRequest.getBrandId());
        fuelService.fuelIdMustBeExists(createModelRequest.getFuelId());
        transmissionService.transmissionIdMustBeExists(createModelRequest.getTransmissionId());
        modelBusinessRules.modelNameCannotBeDuplicated(createModelRequest.getName());

        Model model = this.modelMapperService.forRequest().map(createModelRequest,Model.class);
        Model createdModel = modelRepository.save(model);

        return this.modelMapperService.forResponse().map(createdModel,CreatedModelResponse.class);
    }

    @Override
    public UpdatedModelResponse update(UpdateModelRequest updateModelRequest) {
        modelBusinessRules.modelIdMustBeExists(updateModelRequest.getId());
        brandService.brandIdMustBeExists(updateModelRequest.getBrandId());
        fuelService.fuelIdMustBeExists(updateModelRequest.getFuelId());
        transmissionService.transmissionIdMustBeExists(updateModelRequest.getTransmissionId());
        modelBusinessRules.modelNameCannotBeDuplicated(updateModelRequest.getName());


        Model existingModel = modelRepository.findById(updateModelRequest.getId()).get();


        Model updatedModel = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);

        updatedModel.setCreatedDate(existingModel.getCreatedDate());
        updatedModel = modelRepository.save(updatedModel);

        return modelMapperService.forResponse().map(updatedModel, UpdatedModelResponse.class);
    }

    @Override
    public List<GetModelListResponse> getAll() {
        List<Model> models = modelRepository.findAll();

        return models.stream()
                .map(model -> modelMapperService.forResponse().map(model, GetModelListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetModelResponse getById(int id) {
        modelBusinessRules.modelIdMustBeExists(id);
        Model model = modelRepository.findById(id).get();
        return this.modelMapperService.forResponse().map(model,GetModelResponse.class);

    }

    @Override
    public void modelIdMustBeExists(int id) {
        modelBusinessRules.modelIdMustBeExists(id);
    }


    @Override
    public void delete(int id) {
        modelBusinessRules.modelIdMustBeExists(id);
        modelRepository.deleteById(id);
    }
}
