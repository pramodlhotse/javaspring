package com.spring.java.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "temp_invoice_hdr")
public class TempInvoiceHdr {
    @Id
    @Column(name = "batch_hdr_id")
    private Long batchHdrId;
    @Column(name = "upload_status")
    private String uploadStatus;
    @Column(name = "update_Date")
    private Date updateDate;

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setBatchHdrId(Long batchHdrId) {
        this.batchHdrId = batchHdrId;
    }

    public Long getBatchHdrId() {
        return batchHdrId;
    }
}
