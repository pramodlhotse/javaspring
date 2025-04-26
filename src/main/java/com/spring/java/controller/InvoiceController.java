package com.spring.java.controller;

import com.spring.java.dto.TempInvoiceHrdDTO;
import com.spring.java.entity.InvoiceDetails;
import com.spring.java.entity.InvoiceDetailsItemId;
import com.spring.java.jpa.InvoiceDetailsRepository;
import com.spring.java.jpa.TempInvoiceDtl;
import com.spring.java.service.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@RequestMapping("/api/inovoice")
public class InvoiceController {

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;
    @Autowired
    private TempInvoiceDtl tempInvoiceDtl;

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
    @PostMapping
    public String saveInvoice(@RequestParam long batch_hdr_id, @RequestParam long batch_dtl_id_start, @RequestParam long batch_dtl_id_end) {
        InvoiceDetails invoiceDetails = new InvoiceDetails();
        InvoiceDetailsItemId invoiceDetailsItemId = new InvoiceDetailsItemId();
//        invoiceDetails.setId(invoiceDetailsItemId);
//       for (long i = batch_dtl_id_start; i < batch_dtl_id_end; i++) {
//                invoiceDetails.setBatch_dtl_id(i);
//                invoiceDetailsService.generatesInvoice(invoiceDetails);
//            }
        invoiceDetailsRepository.insert_invoice_hdr(batch_hdr_id);
        System.out.println("Invoice saved");
        return "data saved successfully";

}

    @PostMapping("/jdbc-batch")
    public String jdbcBatchInsert(@RequestParam long batch_hdr_id, @RequestParam long batch_dtl_id_start, @RequestParam long batch_dtl_id_end) {
        try {
            List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();

            for (long i = batch_dtl_id_start; i < batch_dtl_id_end; i++) {
                InvoiceDetailsItemId invoiceDetailsItemId = new InvoiceDetailsItemId();
                invoiceDetailsItemId.setBatch_dtl_id(i);
                invoiceDetailsItemId.setBatch_hdr_id(batch_hdr_id);
                InvoiceDetails invoiceDetails = new InvoiceDetails();
                invoiceDetails.setId(invoiceDetailsItemId);
                invoiceDetailsList.add(invoiceDetails);
            }
            ExecutorService executorService = Executors.newFixedThreadPool(10); // 10 parallel threads

            List<List<InvoiceDetails>> batches = partitionList(invoiceDetailsList, 700);

            for (List<InvoiceDetails> batch : batches) {
                executorService.submit(() -> {
                    invoiceDetailsRepository.batchInsert(batch);

                });
            }

           // Shutdown after all tasks submitted
            executorService.shutdown();

            invoiceDetailsRepository.insert_invoice_hdr(batch_hdr_id);
        }
        catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("This invoice was modified by another user. Please reload and try again.");
        }
        return "data saved successfully";

    }
    public List<List<InvoiceDetails>> partitionList(List<InvoiceDetails> largeList, int batchSize) {
        List<List<InvoiceDetails>> partitions = new ArrayList<>();
        for (int i = 0; i < largeList.size(); i += batchSize) {
            partitions.add(largeList.subList(i, Math.min(i + batchSize, largeList.size())));
        }
        return partitions;
    }
@PostMapping("/save-all")
public String generatesAllInvoice(@RequestParam long batch_hdr_id, @RequestParam long batch_dtl_id_start, @RequestParam long batch_dtl_id_end) {

        try {
            List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();

            for (long i = batch_dtl_id_start; i < batch_dtl_id_end; i++) {
                InvoiceDetailsItemId invoiceDetailsItemId = new InvoiceDetailsItemId();
                invoiceDetailsItemId.setBatch_dtl_id(i);
                invoiceDetailsItemId.setBatch_hdr_id(batch_hdr_id);
                InvoiceDetails invoiceDetails = new InvoiceDetails();
                invoiceDetails.setId(invoiceDetailsItemId);
                invoiceDetailsList.add(invoiceDetails);
            }
            invoiceDetailsService.generatesInvoiceAll(invoiceDetailsList, 100);
            invoiceDetailsRepository.insert_invoice_hdr(batch_hdr_id);

        }
        catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("This invoice was modified by another user. Please reload and try again.");
        }
    return "data saved successfully";
}
    @PutMapping

    public String updateInvoice_hdr(@RequestParam List<Long> batch_hdr_idList) {
        for (long batch_hdr_id : batch_hdr_idList) {
            invoiceDetailsRepository.update_invoice_hdr(batch_hdr_id);
        }

        return "data updated successfully";

    }
    @GetMapping
    public List<TempInvoiceHrdDTO> getAllHeaderIds() {

        return invoiceDetailsService.getAllHeaderIds();
    }
    @GetMapping("/{id}")
    public TempInvoiceHrdDTO getInvoiceHrdById(@PathVariable long id) {
        return invoiceDetailsService.getInvoiceDetailsById(id);
    }


}