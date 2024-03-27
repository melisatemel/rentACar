package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.BrandRepository;

import com.etiya.rentACar.dataAccess.abstracts.ModelRepository;
import com.etiya.rentACar.entities.Model;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private ModelRepository modelRepository;
    public void modelNameCannotBeDuplicated(String modelName){
        Optional<Model> model = modelRepository.findByNameIgnoreCase(modelName);

        if(model.isPresent()){
            throw new BusinessException("Model Exists");
        }
    }
}
