package com.spring.java.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Embeddable
@Data
@Setter
@Getter
public class InvoiceDetailsItemId implements Serializable {
    private Long batch_hdr_id;
    private Long batch_dtl_id;
    public InvoiceDetailsItemId() {

    }
    public InvoiceDetailsItemId(Long batch_hdr_id, Long batch_dtl_id) {
        this.batch_hdr_id = batch_hdr_id;
        this.batch_dtl_id = batch_dtl_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceDetailsItemId that = (InvoiceDetailsItemId) o;
        return batch_hdr_id.equals(that.batch_hdr_id) && batch_dtl_id.equals(that.batch_dtl_id);
    }
    @Override
    public int hashCode() {
        return batch_hdr_id.hashCode() + batch_dtl_id.hashCode();
    }

}
