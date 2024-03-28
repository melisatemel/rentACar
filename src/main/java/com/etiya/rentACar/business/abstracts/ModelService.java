package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.brand.CreateBrandRequest;
import com.etiya.rentACar.business.dtos.requests.brand.UpdateBrandRequest;
import com.etiya.rentACar.business.dtos.requests.model.CreateModelRequest;
import com.etiya.rentACar.business.dtos.requests.model.UpdateModelRequest;
import com.etiya.rentACar.business.dtos.responses.brand.CreatedBrandResponse;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandListResponse;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandResponse;
import com.etiya.rentACar.business.dtos.responses.brand.UpdatedBrandResponse;
import com.etiya.rentACar.business.dtos.responses.model.CreatedModelResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelListResponse;
import com.etiya.rentACar.business.dtos.responses.model.GetModelResponse;
import com.etiya.rentACar.business.dtos.responses.model.UpdatedModelResponse;
import com.etiya.rentACar.entities.Model;

import java.util.List;

public interface ModelService {
    CreatedModelResponse add(CreateModelRequest brand); //CreateBrandRequestten kurallara uygun bana brand ekle(name ekle)
    UpdatedModelResponse update(UpdateModelRequest brand);
    List<GetModelListResponse> getAll();    //markaları listelemek için (yanıt)
    GetModelResponse getById(int id);

    void modelIdMustBeExists(int id);
    void delete(int id);
}
