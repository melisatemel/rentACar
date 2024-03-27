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
            brandRepository.save(brand);

            CreatedBrandResponse response = this.modelMapperService.forResponse().map(brand, CreatedBrandResponse.class);
            return response;

/*
        //mapping
        Brand brand = new Brand();      //eklenecek brand nesnesi oluşturduk
        brand.setName((createBrandRequest.getName()));  //eklenecek brand createBrandRequest'ten gelen name
        brand.setCreatedDate(LocalDateTime.now());      //eklenecek brand LocalDateTime'den gelen tarih

        Brand createdBrand = this.brandRepository.save(brand);

        //mapping
        CreatedBrandResponse createdBrandResponse = new CreatedBrandResponse();
        createdBrandResponse.setId(createdBrand.getId());
        createdBrandResponse.setName((createdBrand.getName()));
        createdBrandResponse.setCreatedDate(createdBrand.getCreatedDate());

        return createdBrandResponse;*/

    }

    public UpdatedBrandResponse update(UpdateBrandRequest brand) {
        brandBusinessRules.brandNameCannotBeDuplicated(brand.getName());
        Brand brandToUpdate = brandRepository.findById(brand.getId()).orElseThrow(()->new IllegalArgumentException("Brand not found!")); //hata fırlatma orElseThrow

        this.modelMapperService.forRequest().map(brand,brandToUpdate);

        brandRepository.save(brandToUpdate);

        UpdatedBrandResponse updatedBrandResponse = modelMapperService.forResponse().map(brandToUpdate,UpdatedBrandResponse.class);

        return updatedBrandResponse;
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
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand not found"));
        GetBrandResponse getBrandResponse =this.modelMapperService.forResponse().map(brand,GetBrandResponse.class);
        return getBrandResponse;
    }
    @Override
    public void delete(int id){
        brandRepository.deleteById(id);
    }
}

//Tüm entitiler için Add, Update,Delete,GetAll,GetById operasyonlarını uçtan uca yaz
//tamamında response-request patern uygulanmalı


