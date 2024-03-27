package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.TransmissionService;
import com.etiya.rentACar.business.dtos.requests.transmission.CreateTransmissionRequest;
import com.etiya.rentACar.business.dtos.requests.transmission.UpdateTransmissionRequest;
import com.etiya.rentACar.business.dtos.responses.transmission.CreatedTransmissionResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.GetTransmissionListResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.GetTransmissionResponse;
import com.etiya.rentACar.business.dtos.responses.transmission.UpdatedTransmissionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController //Restful yapısın demek yani erişim noktası
@AllArgsConstructor
@RequestMapping("api/v1/transmission") //localhost:8081/api/v1/brands Adresleme=erişim noktasıyla controller arası iletişimi kurar
public class TransmissionController {
    private TransmissionService transmissionService;
    @GetMapping
    public List<GetTransmissionListResponse> getAll(){
        return transmissionService.getAll();
    }
    @GetMapping("/{id}")
    public GetTransmissionResponse getById(@PathVariable int id){
        return transmissionService.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse add(@RequestBody CreateTransmissionRequest createTransmissionRequest){
        return transmissionService.add(createTransmissionRequest);
    }
    @PutMapping
    public UpdatedTransmissionResponse update(@RequestBody UpdateTransmissionRequest updateTransmissionRequest){
        return transmissionService.update(updateTransmissionRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        transmissionService.delete(id);
    }

}
