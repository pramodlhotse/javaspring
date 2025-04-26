package com.spring.java.service;

import com.spring.java.dto.TempInvoiceHrdDTO;
import com.spring.java.entity.InvoiceDetails;

import java.util.List;


public interface InvoiceDetailsService {
    void generatesInvoice(InvoiceDetails invoiceDetails);
    void generatesInvoiceAll(List<InvoiceDetails> invoiceDetails, int batchSize);
    List<TempInvoiceHrdDTO> getAllHeaderIds();

    TempInvoiceHrdDTO getInvoiceDetailsById(long id);
}
