package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.dtos.requests.brand.CreateBrandRequest;
import com.etiya.rentACar.business.dtos.requests.brand.UpdateBrandRequest;
import com.etiya.rentACar.business.dtos.responses.brand.CreatedBrandResponse;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandListResponse;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandResponse;
import com.etiya.rentACar.business.dtos.responses.brand.UpdatedBrandResponse;
import com.etiya.rentACar.business.rules.BrandBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.BrandRepository;
import com.etiya.rentACar.entities.Brand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service    //Bu sınıf bir business nesnesidir. IoC de oluşturulacak.
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;    //brandmanager dataaccess i kullanır
    private final ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;
    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {

        brandBusinessRules.brandNameCannotBeDuplicated(createBrandRequest.getName()); // iş kuralını yazdığımız yerden
            //İş kuralları ayrıntılı yazılacak  managerların içinde if olmamalı

            Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);    //.class obje çağırır
            Brand savedBrand = brandRepository.save(brand);

        return this.modelMapperService.forResponse().map(savedBrand, CreatedBrandResponse.class);

    }

    public UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest) {
        brandBusinessRules.brandIdMustBeExists(updateBrandRequest.getId());
        brandBusinessRules.brandNameCannotBeDuplicated(updateBrandRequest.getName());
        Brand brandToUpdate = brandRepository.findById(updateBrandRequest.getId()).get();

        this.modelMapperService.forRequest().map(updateBrandRequest,brandToUpdate);

        Brand updatedBrand = brandRepository.save(brandToUpdate);

        return modelMapperService.forResponse().map(updatedBrand,UpdatedBrandResponse.class);
    }

    @Override
    public List<GetBrandListResponse> getAll() {
        List<Brand> brands = brandRepository.findAll(); //BrandServisimizin getAll u Repository mizin findAll unu çağırır
        List<GetBrandListResponse> brandListResponses = brands.stream().map(brand ->    //stream içeride listleri geziyor forla
                this.modelMapperService.forResponse()
                        .map(brand,GetBrandListResponse.class)).collect(Collectors.toList());

        //stream() = Liste varsa tek tek dolaşıyor.            map() = Her marka için bir mapleme yap(id,name)  collect()=
        return brandListResponses;
    }


    @Override
    public GetBrandResponse getById(int id) {   //
        brandBusinessRules.brandIdMustBeExists(id);
        Brand brand = brandRepository.findById(id).get();

        return this.modelMapperService.forResponse().map(brand,GetBrandResponse.class);
    }
    @Override
    public void delete(int id){
        brandBusinessRules.brandIdMustBeExists(id);
        brandRepository.deleteById(id);
    }
}

//Tüm entitiler için Add, Update,Delete,GetAll,GetById operasyonlarını uçtan uca yaz
//tamamında response-request patern uygulanmalı


