package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.TransmissionRepository;
import com.etiya.rentACar.entities.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransmissionBusinessRules {
    private TransmissionRepository transmissionRepository;
    public void transmissionNameCannotBeDuplicated(String transmissionName){
        Optional<Transmission> transmission = transmissionRepository.findByNameIgnoreCase(transmissionName);

        if(transmission.isPresent()){
            throw new BusinessException("Transmission Exists");
        }
    }

    public void transmissionIdMustBeExists(int id){
        if(!transmissionRepository.existsById(id)){
            throw new BusinessException("Transmission id must be exists");
        }
    }

}
