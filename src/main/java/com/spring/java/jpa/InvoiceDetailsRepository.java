package com.spring.java.jpa;

import com.spring.java.entity.InvoiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@Repository
public class InvoiceDetailsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public InvoiceDetails findById(Long id) {
        String sql = "SELECT * FROM invoice_details WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(InvoiceDetails.class), id);
    }


    public int insert(InvoiceDetails invoice) {
        String sql = "INSERT INTO temp_invoice_dtl (batch_hdr_id, batch_dtl_id, receipt_type, first_name, " +
                "last_name, email, mobile_no, customer_tin, customer_addr, transact_date, snr_id_no, " +
                "alipay_transact_id, mambu_transact_id, quantity, product, a_transact_desc, transact_type," +
                "transact_amt, penalty_amt, interest_amt, fees_amt, process_fee, less_vat, net_vat_total," +
                "less_withhold_tax, net_vat_withhold_tax, add_12_vat, total_amt_due, vatable_sales, vat_exempt_sales," +
                "zero_rated_sales, add_vat_minus_12, total_sales, update_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, invoice.getId().getBatch_hdr_id(), invoice.getId().getBatch_dtl_id(), invoice.getReceipt_type(), invoice.getFirst_name(),
                invoice.getLast_name(), invoice.getEmail(), invoice.getMobile_no(), invoice.getCustomer_tin(), invoice.getCustomer_addr(), invoice.getTransact_date(),
                invoice.getSnr_id_no(),  invoice.getAlipay_transact_id(), invoice.getMambu_transact_id(), invoice.getQuantity(), invoice.getProduct(),
                invoice.getA_transact_desc(), invoice.getTransact_type(), invoice.getTransact_amt(), invoice.getPenalty_amt(), invoice.getInterest_amt(),
                invoice.getFees_amt(), invoice.getProcess_fee(), invoice.getLess_vat(), invoice.getNet_vat_total(), invoice.getLess_withhold_tax(),
                invoice.getNet_vat_withhold_tax(), invoice.getAdd_12_vat(), invoice.getTotal_amt_due(), invoice.getVatable_sales(), invoice.getVat_exempt_sales(),
                invoice.getZero_rated_sales(), invoice.getAdd_vat_minus_12(), invoice.getTotal_sales(), invoice.getUpdate_date()
        );
    }

    public int batchInsert(List<InvoiceDetails> invoices) {
        String sql = "INSERT INTO temp_invoice_dtl (batch_hdr_id, batch_dtl_id, receipt_type, first_name, " +
                "last_name, email, mobile_no, customer_tin, customer_addr, transact_date, snr_id_no, " +
                "alipay_transact_id, mambu_transact_id, quantity, product, a_transact_desc, transact_type," +
                "transact_amt, penalty_amt, interest_amt, fees_amt, process_fee, less_vat, net_vat_total," +
                "less_withhold_tax, net_vat_withhold_tax, add_12_vat, total_amt_due, vatable_sales, vat_exempt_sales," +
                "zero_rated_sales, add_vat_minus_12, total_sales, update_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //return jdbcTemplate.update(sql,invoices);

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                InvoiceDetails invoice = invoices.get(i);
                ps.setLong(1, invoice.getId().getBatch_hdr_id());
                ps.setLong(2, invoice.getId().getBatch_dtl_id());      // 👈 This one is likely missing
                ps.setString(3, invoice.getReceipt_type());
                ps.setString(4,  invoice.getFirst_name());
                ps.setString(5,  invoice.getLast_name());
                ps.setString(6,  invoice.getEmail());
                ps.setString(7,  invoice.getMobile_no());
                ps.setString(8,  invoice.getCustomer_tin());
                ps.setString(9,  invoice.getCustomer_addr());
                ps.setString(10, null);
                ps.setString(11,  invoice.getSnr_id_no());
                ps.setString(12,  invoice.getAlipay_transact_id());
                ps.setString(13,  invoice.getMambu_transact_id());
                ps.setString(14, String.valueOf(invoice.getQuantity()));
                ps.setString(15,  invoice.getProduct());
                ps.setString(16,  invoice.getA_transact_desc());
                ps.setString(17,  invoice.getTransact_type());
                ps.setString(18, String.valueOf(invoice.getTransact_amt()));
                ps.setString(19, String.valueOf(invoice.getPenalty_amt()));
                ps.setString(20, String.valueOf(invoice.getInterest_amt()));
                ps.setString(21, String.valueOf(invoice.getFees_amt()));
                ps.setString(22, String.valueOf(invoice.getProcess_fee()));
                ps.setString(23, String.valueOf(invoice.getLess_vat()));
                ps.setString(24, String.valueOf(invoice.getNet_vat_total()));
                ps.setString(25, String.valueOf(invoice.getLess_withhold_tax()));
                ps.setString(26, String.valueOf(invoice.getNet_vat_withhold_tax()));
                ps.setString(27, String.valueOf(invoice.getAdd_12_vat()));
                ps.setString(28, String.valueOf(invoice.getTotal_amt_due()));
                ps.setString(29, String.valueOf(invoice.getVatable_sales()));
                ps.setString(30, String.valueOf(invoice.getVat_exempt_sales()));
                ps.setString(31, String.valueOf(invoice.getZero_rated_sales()));
                ps.setString(32, String.valueOf(invoice.getAdd_vat_minus_12()));
                ps.setString(33, String.valueOf(invoice.getTotal_sales()));
                ps.setString(34, null);


            }

            @Override
            public int getBatchSize() {
                return invoices.size();
            }
        });
        return 1;
    }

    public int insert_invoice_hdr(long batchHdrId) {
        String sql = "INSERT INTO temp_invoice_hdr (batch_hdr_id, upload_status, update_date) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, batchHdrId, "COMPLETED", new Date());
    }


    public int update_invoice_hdr(long batchHdrId) {

        String sql = "delete from temp_invoice_hdr where batch_hdr_id = ?";
        String deleteSql = "DELETE FROM fuse_invoice_dtl where process_id in (select process_id from fuse_invoice_hdr where batch_hdr_id = ?)";
        String deleteSql1 = "DELETE FROM fuse_invoice_hdr where batch_hdr_id = ?";
        jdbcTemplate.update(deleteSql, batchHdrId);
        jdbcTemplate.update(deleteSql1, batchHdrId);
        jdbcTemplate.update(sql, batchHdrId);

        return insert_invoice_hdr(batchHdrId);
    }
}
