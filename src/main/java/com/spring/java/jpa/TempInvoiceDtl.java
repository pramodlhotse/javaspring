package com.spring.java.jpa;

import com.spring.java.entity.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempInvoiceDtl extends JpaRepository<InvoiceDetails, Long> {
}
