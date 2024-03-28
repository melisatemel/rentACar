package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.brand.CreateBrandRequest;
import com.etiya.rentACar.business.dtos.requests.brand.UpdateBrandRequest;
import com.etiya.rentACar.business.dtos.responses.brand.CreatedBrandResponse;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandListResponse;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandResponse;
import com.etiya.rentACar.business.dtos.responses.brand.UpdatedBrandResponse;
import com.etiya.rentACar.entities.Brand;

import java.util.List;

public interface BrandService {
    //İş kurallarını yazacağımız yapılar yazılır
    CreatedBrandResponse add(CreateBrandRequest brand); //CreateBrandRequestten kurallara uygun bana brand ekle(name ekle)
    UpdatedBrandResponse update(UpdateBrandRequest brand);
    List<GetBrandListResponse> getAll();    //markaları listelemek için (yanıt)
    GetBrandResponse getById(int id);

    void brandIdMustBeExists(int id);
    void delete(int id);
}
