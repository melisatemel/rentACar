package com.etiya.rentACar.business.rules;

import com.etiya.rentACar.core.exceptions.types.BusinessException;
import com.etiya.rentACar.dataAccess.abstracts.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InvoiceBusinessRules {
    private final InvoiceRepository invoiceRepository;

    public void invoiceIdMustBeExist(int invoiceId){
        if(!invoiceRepository.existsById(invoiceId)){
            throw new BusinessException("Invoice Not Found");
        }
    }


}
