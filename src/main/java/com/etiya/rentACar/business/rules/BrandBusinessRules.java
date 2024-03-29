package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.BrandRepository;
import com.etiya.rentACar.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor //injection ı bu sayede gerçekleştirir
public class BrandBusinessRules {
    private BrandRepository brandRepository;
    public void brandNameCannotBeDuplicated(String brandName){
        //trim = veritabanındaki sağdan soldan boşlukları görmezden gelir.
        Optional<Brand> brand = brandRepository.findByNameIgnoreCase(brandName.trim());    //Optional = bir değeri var yada yok demek bir değer döndürür yada null

        if(brand.isPresent()){  //hata varsa
           throw new BusinessException("Brand Exists");
        }
    }

    public void brandIdMustExist(int id){
        Optional<Brand> brand = brandRepository.findById(id);
        if(brand.isEmpty()){
            throw new BusinessException("Brand not found");
        }
        else if(brand.get().getDeletedDate() != null){
            throw new BusinessException("Brand not found");
        }
    }





}
