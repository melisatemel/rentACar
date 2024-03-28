package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.InvoiceService;
import com.etiya.rentACar.business.dtos.requests.invoice.CreateInvoiceRequest;
import com.etiya.rentACar.business.dtos.requests.invoice.UpdateInvoiceRequest;
import com.etiya.rentACar.business.dtos.responses.invoice.CreatedInvoiceResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.GetInvoiceListResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.GetInvoiceResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.UpdatedInvoiceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/invoices")
public class InvoicesController {
    private InvoiceService invoiceService;

    @GetMapping
    public List<GetInvoiceListResponse> getAll(){
        return invoiceService.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(int id){
        return invoiceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedInvoiceResponse add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest){
        return invoiceService.add(createInvoiceRequest);
    }

    @PutMapping
    public UpdatedInvoiceResponse update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest){
        return invoiceService.update(updateInvoiceRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        invoiceService.delete(id);
    }

}
