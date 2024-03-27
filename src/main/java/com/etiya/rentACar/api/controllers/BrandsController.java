package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.dtos.requests.brand.CreateBrandRequest;
import com.etiya.rentACar.business.dtos.requests.brand.UpdateBrandRequest;
import com.etiya.rentACar.business.dtos.responses.brand.CreatedBrandResponse;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandListResponse;
import com.etiya.rentACar.business.dtos.responses.brand.GetBrandResponse;
import com.etiya.rentACar.business.dtos.responses.brand.UpdatedBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Restful yapısın demek yani erişim noktası
@AllArgsConstructor
@RequestMapping("api/v1/brands") //localhost:8081/api/v1/brands Adresleme=erişim noktasıyla controller arası iletişimi kurar
public class BrandsController {

    private BrandService brandService;      //istek business a gidiyor

    //İSTEK CONTROLLER E GELİR İSTEK UYGUNSA BUSİNESS E GİDİLİR İŞ KURALLARINA BAKILIR UYGUNSA,
    //BUSİNESS DATAACCESSE E GİDER HER ŞEY UYGUN DATAYI VER DER

    //CONTROLLER' lar yazdığımız kodu dış dünyay açar

    @GetMapping     //getAll a erişimi sağlar data çekmek için
    public List<GetBrandListResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")    //getById a erişimi sağlar data çekmek için
    public GetBrandResponse getById(@PathVariable int id) { //@PathVariable demek git pathten oku
        return brandService.getById(id);
    }

    @PostMapping    //Veri EKLEME
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBrandResponse add(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);    //createBrandRequest' i, brandService'e gönder
    }

    @PutMapping //Güncelleme
    public UpdatedBrandResponse update(@Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
        return brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {  //pathten okuyup sil
        brandService.delete(id);
    }

}
