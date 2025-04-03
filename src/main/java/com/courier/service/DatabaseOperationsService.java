package com.courier.service;

import com.courier.model.Customer;
import com.courier.model.Packet;
import com.courier.model.Payment;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DatabaseOperationsService {
    // Stored Procedure Operations
    void createCustomerUsingSP(Customer customer);
    void updateCustomerUsingSP(Integer customerId, Customer customer);
    void addPacketUsingSP(Packet packet);
    void deletePacketUsingSP(Integer packetId);
    void processPaymentUsingSP(Payment payment);

    // Function Operations
    Map<String, Object> getTotalPaymentsForCustomer(Integer customerId);
    Map<String, Object> getPacketStatusByPacketId(Integer packetId);
    Map<String, Object> getEmployeeWorkloadById(Integer employeeId);

    // View Operations
    List<Map<String, Object>> getCustomerShipments();
    List<Map<String, Object>> getCustomerTotalSpent();
    List<Map<String, Object>> getEmployeeWorkload();
    List<Map<String, Object>> getRecentDeliveries();
    List<Map<String, Object>> getShipmentStatusSummary();
    List<Map<String, Object>> getTopPayingCustomers();
}