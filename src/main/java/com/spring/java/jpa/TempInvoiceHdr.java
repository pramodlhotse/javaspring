package com.spring.java.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempInvoiceHdr extends JpaRepository<com.spring.java.entity.TempInvoiceHdr, Long>{

}
