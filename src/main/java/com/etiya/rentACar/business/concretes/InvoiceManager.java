package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.InvoiceService;
import com.etiya.rentACar.business.dtos.requests.invoice.CreateInvoiceRequest;
import com.etiya.rentACar.business.dtos.requests.invoice.UpdateInvoiceRequest;
import com.etiya.rentACar.business.dtos.responses.invoice.CreatedInvoiceResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.GetInvoiceListResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.GetInvoiceResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.UpdatedInvoiceResponse;
import com.etiya.rentACar.business.rules.InvoiceBusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.etiya.rentACar.entities.Invoice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceBusinessRules invoiceBusinessRules;
    private final ModelMapperService modelMapperService;


    @Override
    public CreatedInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        invoiceRepository.save(invoice);
        return modelMapperService.forResponse().map(invoice, CreatedInvoiceResponse.class);

    }

    @Override
    public UpdatedInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest) {
        invoiceBusinessRules.invoiceIdMustBeExist(updateInvoiceRequest.getId());

        Invoice existingInvoice = invoiceRepository.findById(updateInvoiceRequest.getId()).orElseThrow(() -> new RuntimeException("Invoice not found"));

        modelMapperService.forRequest().map(updateInvoiceRequest, existingInvoice);

        Invoice updatedInvoice = invoiceRepository.save(existingInvoice);

        return modelMapperService.forResponse().map(updatedInvoice, UpdatedInvoiceResponse.class);
    }

    @Override
    public List<GetInvoiceListResponse> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(invoice -> this.modelMapperService.forResponse()
                        .map(invoice, GetInvoiceListResponse.class)).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        return modelMapperService.forResponse().map(invoice, GetInvoiceResponse.class);
    }

    @Override
    public void delete(int id) {
        invoiceBusinessRules.invoiceIdMustBeExist(id);
        invoiceRepository.deleteById(id);

    }
}
