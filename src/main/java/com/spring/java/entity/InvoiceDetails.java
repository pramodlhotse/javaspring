package com.spring.java.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "temp_invoice_dtl")
public class InvoiceDetails implements Serializable  {
    private static final long serialVersionUID = 2963010471811315930L;

      @EmbeddedId
      private InvoiceDetailsItemId id;

     private String receipt_type = "SERVICE_INVOICE";
     private String first_name = "MATTHEW";

     private String last_name = "POAKO";
     private String email = "pramod.nkumar@ascendion.com";
     private String mobile_no = "9844609719";
     private String customer_tin = "123456";
     private String customer_addr = "1570 Monggo St Manggahan RIZAL";
     private Date transact_date = new Date();
     private String snr_id_no = "N";
     private String alipay_transact_id = "1021144859465";
    private String mambu_transact_id = "1427332329";
    private Long quantity = 1L;
    private String product = "GLoan";
    private String a_transact_desc = "GLoan FEE_CHARGED for Loan Account SWQJ8736";
    private String transact_type = "FEE_CHARGED";
    private Double transact_amt = 60.0;
    private Double penalty_amt = 0.00;
    private Double interest_amt = 60.00;
    private Double fees_amt = 60.00;
    private Double process_fee = 60.00;
    private Double less_vat = 6.43;
    private Double net_vat_total = 53.57;
    private Double less_withhold_tax = 0.00;
    private Double net_vat_withhold_tax = 53.57;
    private Double add_12_vat = 6.43;
    private Double total_amt_due = 60.00;
    private Double vatable_sales = 53.57;
    private Double vat_exempt_sales = 0.00;
    private Double zero_rated_sales = 0.00;
    private Double add_vat_minus_12 = 6.43;
    private Double total_sales = 60.00;
    private Date update_date = new Date();

    public InvoiceDetailsItemId getId() {
        return id;
    }

    public void setId(InvoiceDetailsItemId id) {
        this.id = id;
    }

    public String getReceipt_type() {
        return receipt_type;
    }

    public void setReceipt_type(String receipt_type) {
        this.receipt_type = receipt_type;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getCustomer_tin() {
        return customer_tin;
    }

    public void setCustomer_tin(String customer_tin) {
        this.customer_tin = customer_tin;
    }

    public String getCustomer_addr() {
        return customer_addr;
    }

    public void setCustomer_addr(String customer_addr) {
        this.customer_addr = customer_addr;
    }

    public Date getTransact_date() {
        return transact_date;
    }

    public void setTransact_date(Date transact_date) {
        this.transact_date = transact_date;
    }

    public String getSnr_id_no() {
        return snr_id_no;
    }

    public void setSnr_id_no(String snr_id_no) {
        this.snr_id_no = snr_id_no;
    }

    public String getAlipay_transact_id() {
        return alipay_transact_id;
    }

    public void setAlipay_transact_id(String alipay_transact_id) {
        this.alipay_transact_id = alipay_transact_id;
    }

    public String getMambu_transact_id() {
        return mambu_transact_id;
    }

    public void setMambu_transact_id(String mambu_transact_id) {
        this.mambu_transact_id = mambu_transact_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getA_transact_desc() {
        return a_transact_desc;
    }

    public void setA_transact_desc(String a_transact_desc) {
        this.a_transact_desc = a_transact_desc;
    }

    public String getTransact_type() {
        return transact_type;
    }

    public void setTransact_type(String transact_type) {
        this.transact_type = transact_type;
    }

    public Double getTransact_amt() {
        return transact_amt;
    }

    public void setTransact_amt(Double transact_amt) {
        this.transact_amt = transact_amt;
    }

    public Double getPenalty_amt() {
        return penalty_amt;
    }

    public void setPenalty_amt(Double penalty_amt) {
        this.penalty_amt = penalty_amt;
    }

    public Double getInterest_amt() {
        return interest_amt;
    }

    public void setInterest_amt(Double interest_amt) {
        this.interest_amt = interest_amt;
    }

    public Double getFees_amt() {
        return fees_amt;
    }

    public void setFees_amt(Double fees_amt) {
        this.fees_amt = fees_amt;
    }

    public Double getProcess_fee() {
        return process_fee;
    }

    public void setProcess_fee(Double process_fee) {
        this.process_fee = process_fee;
    }

    public Double getLess_vat() {
        return less_vat;
    }

    public void setLess_vat(Double less_vat) {
        this.less_vat = less_vat;
    }

    public Double getNet_vat_total() {
        return net_vat_total;
    }

    public void setNet_vat_total(Double net_vat_total) {
        this.net_vat_total = net_vat_total;
    }

    public Double getLess_withhold_tax() {
        return less_withhold_tax;
    }

    public void setLess_withhold_tax(Double less_withhold_tax) {
        this.less_withhold_tax = less_withhold_tax;
    }

    public Double getNet_vat_withhold_tax() {
        return net_vat_withhold_tax;
    }

    public void setNet_vat_withhold_tax(Double net_vat_withhold_tax) {
        this.net_vat_withhold_tax = net_vat_withhold_tax;
    }

    public Double getAdd_12_vat() {
        return add_12_vat;
    }

    public void setAdd_12_vat(Double add_12_vat) {
        this.add_12_vat = add_12_vat;
    }

    public Double getTotal_amt_due() {
        return total_amt_due;
    }

    public void setTotal_amt_due(Double total_amt_due) {
        this.total_amt_due = total_amt_due;
    }

    public Double getVatable_sales() {
        return vatable_sales;
    }

    public void setVatable_sales(Double vatable_sales) {
        this.vatable_sales = vatable_sales;
    }

    public Double getVat_exempt_sales() {
        return vat_exempt_sales;
    }

    public void setVat_exempt_sales(Double vat_exempt_sales) {
        this.vat_exempt_sales = vat_exempt_sales;
    }

    public Double getZero_rated_sales() {
        return zero_rated_sales;
    }

    public void setZero_rated_sales(Double zero_rated_sales) {
        this.zero_rated_sales = zero_rated_sales;
    }

    public Double getAdd_vat_minus_12() {
        return add_vat_minus_12;
    }


    public void setAdd_vat_minus_12(Double add_vat_minus_12) {
        this.add_vat_minus_12 = add_vat_minus_12;
    }

    public Double getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(Double total_sales) {
        this.total_sales = total_sales;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

}
