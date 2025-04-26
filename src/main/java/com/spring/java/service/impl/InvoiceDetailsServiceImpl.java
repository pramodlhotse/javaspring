package com.spring.java.service.impl;

import com.spring.java.dto.TempInvoiceHrdDTO;
import com.spring.java.entity.InvoiceDetails;
import com.spring.java.jpa.InvoiceDetailsRepository;
import com.spring.java.jpa.TempInvoiceDtl;
import com.spring.java.jpa.TempInvoiceHdr;
import com.spring.java.service.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {

    @Autowired
    private TempInvoiceDtl tempInvoiceDtl;
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
    @Autowired
    private TempInvoiceHdr tempInvoiceHdr;

    @Override
    public void generatesInvoice(InvoiceDetails invoiceDetails) {
        invoiceDetailsRepository.insert(invoiceDetails);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void generatesInvoiceAll(List<InvoiceDetails> invoiceDetails, int batchSize) {
        int i = 0;
        List<InvoiceDetails> batch = new ArrayList<>();

        for (InvoiceDetails e : invoiceDetails) {
            batch.add(e);
            if (++i % batchSize == 0) {
                tempInvoiceDtl.saveAll(batch);
                tempInvoiceDtl.flush();
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            tempInvoiceDtl.saveAll(batch);
            tempInvoiceDtl.flush();
        }

    }

    @Override
    public List<TempInvoiceHrdDTO> getAllHeaderIds() {
        List<com.spring.java.entity.TempInvoiceHdr> tempInvoiceHdrEntity = tempInvoiceHdr.findAll();

        return tempInvoiceHdrEntity.stream().map(this::convertEntityToDto).toList();
    }

    @Override
    public TempInvoiceHrdDTO getInvoiceDetailsById(long id) {
        Optional<com.spring.java.entity.TempInvoiceHdr> tempInvoiceHdrEntity = tempInvoiceHdr.findById(id);;

        return tempInvoiceHdrEntity.map(this::convertEntityToDto).orElse(null);
    }

    private <R> TempInvoiceHrdDTO convertEntityToDto(com.spring.java.entity.TempInvoiceHdr tempInvoiceHdr) {
        TempInvoiceHrdDTO tempInvoiceHrdDTO = new TempInvoiceHrdDTO();
        tempInvoiceHrdDTO.setBatchHdrId(tempInvoiceHdr.getBatchHdrId());
        tempInvoiceHrdDTO.setUploadStatus(tempInvoiceHdr.getUploadStatus());
        return tempInvoiceHrdDTO;

    }


}
