package com.courier.service.impl;

import com.courier.model.Customer;
import com.courier.model.Packet;
import com.courier.model.Payment;
import com.courier.service.DatabaseOperationsService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseOperationsServiceImpl implements DatabaseOperationsService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseOperationsServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void createCustomerUsingSP(Customer customer) {
        String sql = "EXEC sp_CreateCustomer @FirstName=?, @LastName=?, @Phone=?, @Email=?, @Address=?, @NationalID=?, @PostalCode=?";
        jdbcTemplate.update(sql,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getNationalId(),
                customer.getPostalCode());
    }

    @Override
    @Transactional
    public void updateCustomerUsingSP(Integer customerId, Customer customer) {
        String sql = "EXEC sp_UpdateCustomer @CustomerID=?, @FirstName=?, @LastName=?, @Phone=?, @Email=?, @Address=?, @PostalCode=?";
        jdbcTemplate.update(sql,
                customerId,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPostalCode());
    }

    @Override
    @Transactional
    public void addPacketUsingSP(Packet packet) {
        String sql = "EXEC sp_AddPacket @SenderID=?, @RecieverID=?, @Weight=?, @Size=?, @PacketType=?, @Price=?, " +
                    "@StatusID=?, @SendDate=?, @Fragile=?, @PostmanID=?, @RegistererID=?, @DeliveryType=?";
        jdbcTemplate.update(sql,
                packet.getSenderId(),
                packet.getRecieverId(),
                packet.getWeight(),
                packet.getSize(),
                packet.getPacketType(),
                packet.getPrice(),
                packet.getStatusId(),
                packet.getSendDate(),
                packet.getFragile(),
                packet.getPostmanId(),
                packet.getRegistererId(),
                packet.getDeliveryType());
    }

    @Override
    @Transactional
    public void deletePacketUsingSP(Integer packetId) {
        String sql = "EXEC sp_DeletePacket @PacketID=?";
        jdbcTemplate.update(sql, packetId);
    }

    @Override
    @Transactional
    public void processPaymentUsingSP(Payment payment) {
        String sql = "EXEC sp_ProcessPayment @PacketID=?, @Amount=?, @PaymentDate=?, @PaymentMethod=?, " +
                    "@TransactionID=?, @DiscountCode=?, @PaymentStatus=?, @TaxAmount=?";
        jdbcTemplate.update(sql,
                payment.getPacketId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentMethod(),
                payment.getTransactionId(),
                payment.getDiscountCode(),
                payment.getPaymentStatus(),
                payment.getTaxAmount());
    }

    @Override
    public Map<String, Object> getTotalPaymentsForCustomer(Integer customerId) {
        String sql = "SELECT * FROM GetTotalPaymentsForCustomer(?)";
        return jdbcTemplate.queryForMap(sql, customerId);
    }

    @Override
    public Map<String, Object> getPacketStatusByPacketId(Integer packetId) {
        String sql = "SELECT * FROM GetPacketStatusByPacketID(?)";
        return jdbcTemplate.queryForMap(sql, packetId);
    }

    @Override
    public Map<String, Object> getEmployeeWorkloadById(Integer employeeId) {
        String sql = "SELECT * FROM GetEmployeeWorkloadByID(?)";
        return jdbcTemplate.queryForMap(sql, employeeId);
    }

    @Override
    public List<Map<String, Object>> getCustomerShipments() {
        String sql = "SELECT * FROM CustomerShipments";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getCustomerTotalSpent() {
        String sql = "SELECT * FROM CustomerTotalSpent";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getEmployeeWorkload() {
        String sql = "SELECT * FROM EmployeeWorkload";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getRecentDeliveries() {
        String sql = "SELECT * FROM RecentDeliveries";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getShipmentStatusSummary() {
        String sql = "SELECT * FROM ShipmentStatusSummary";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getTopPayingCustomers() {
        String sql = "SELECT * FROM TopPayingCustomers";
        return jdbcTemplate.queryForList(sql);
    }
}