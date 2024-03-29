package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.ModelService;
import com.etiya.rentACar.business.dtos.requests.model.CreateModelRequest;
import com.etiya.rentACar.business.dtos.requests.model.UpdateModelRequest;
import com.etiya.rentACar.business.dtos.responses.model.CreatedModelResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelListResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelResponse;
import com.etiya.rentACar.business.dtos.responses.model.UpdatedModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Restful yapısın demek yani erişim noktası
@AllArgsConstructor
@RequestMapping("api/v1/models") //localhost:8081/api/v1/brands Adresleme=erişim noktasıyla controller arası iletişimi kurar
public class ModelsController {
    private ModelService modelService;

    @GetMapping
    public List<GetModelListResponse> getAll(){

        return modelService.getAll();
    }
    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable int id){
        return modelService.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@RequestBody @Valid CreateModelRequest createModelRequest){

        return modelService.add(createModelRequest);
    }
    @PutMapping
    public UpdatedModelResponse update(@RequestBody @Valid UpdateModelRequest updateModelRequest){
        return modelService.update(updateModelRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        modelService.delete(id);
    }

}
