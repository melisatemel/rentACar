package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.requests.invoice.CreateInvoiceRequest;
import com.etiya.rentACar.business.dtos.requests.invoice.UpdateInvoiceRequest;
import com.etiya.rentACar.business.dtos.responses.invoice.CreatedInvoiceResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.GetInvoiceListResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.GetInvoiceResponse;
import com.etiya.rentACar.business.dtos.responses.invoice.UpdatedInvoiceResponse;

import java.util.List;

public interface InvoiceService {

    CreatedInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
    UpdatedInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest);

    List<GetInvoiceListResponse> getAll();

    GetInvoiceResponse getById(int id);
    void delete(int id);
}
