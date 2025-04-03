package com.courier.service.impl;

import com.courier.model.Payment;
import com.courier.service.PaymentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final JdbcTemplate jdbcTemplate;

    public PaymentServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Payment createPayment(Payment payment) {
        String sql = "INSERT INTO Payments (PacketID, Amount, PaymentDate, PaymentMethod, " +
                    "TransactionID, DiscountCode, PaymentStatus, TaxAmount) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, payment.getPacketId());
            ps.setBigDecimal(2, payment.getAmount());
            ps.setDate(3, java.sql.Date.valueOf(payment.getPaymentDate()));
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getTransactionId());
            ps.setString(6, payment.getDiscountCode());
            ps.setString(7, payment.getPaymentStatus());
            ps.setBigDecimal(8, payment.getTaxAmount());
            return ps;
        }, keyHolder);

        payment.setPaymentId(keyHolder.getKey().intValue());
        return payment;
    }

    @Override
    @Transactional
    public Payment updatePayment(Integer paymentId, Payment payment) {
        String sql = "UPDATE Payments SET PacketID = ?, Amount = ?, PaymentDate = ?, " +
                    "PaymentMethod = ?, TransactionID = ?, DiscountCode = ?, " +
                    "PaymentStatus = ?, TaxAmount = ? WHERE PaymentID = ?";
        
        jdbcTemplate.update(sql,
                payment.getPacketId(),
                payment.getAmount(),
                java.sql.Date.valueOf(payment.getPaymentDate()),
                payment.getPaymentMethod(),
                payment.getTransactionId(),
                payment.getDiscountCode(),
                payment.getPaymentStatus(),
                payment.getTaxAmount(),
                paymentId);

        return getPaymentById(paymentId);
    }

    @Override
    @Transactional
    public void deletePayment(Integer paymentId) {
        String sql = "DELETE FROM Payments WHERE PaymentID = ?";
        jdbcTemplate.update(sql, paymentId);
    }

    @Override
    public Payment getPaymentById(Integer paymentId) {
        String sql = "SELECT * FROM Payments WHERE PaymentID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Payment.class), paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM Payments";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payment.class));
    }

    @Override
    public List<Payment> getPaymentsByPacket(Integer packetId) {
        String sql = "SELECT * FROM Payments WHERE PacketID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payment.class), packetId);
    }

    @Override
    public List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM Payments WHERE PaymentDate BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, 
            new BeanPropertyRowMapper<>(Payment.class), 
            java.sql.Date.valueOf(startDate),
            java.sql.Date.valueOf(endDate));
    }

    @Override
    public List<Payment> getPaymentsByStatus(String status) {
        String sql = "SELECT * FROM Payments WHERE PaymentStatus = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payment.class), status);
    }

    @Override
    public List<Payment> getPaymentsByMethod(String method) {
        String sql = "SELECT * FROM Payments WHERE PaymentMethod = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payment.class), method);
    }

    @Override
    public List<Payment> getPaymentsWithDiscount() {
        String sql = "SELECT * FROM Payments WHERE DiscountCode IS NOT NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payment.class));
    }

    @Override
    public List<Payment> getPendingPayments() {
        String sql = "SELECT * FROM Payments WHERE PaymentStatus = 'pending'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payment.class));
    }
} 