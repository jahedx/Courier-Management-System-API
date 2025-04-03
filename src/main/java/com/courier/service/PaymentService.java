package com.courier.service;

import com.courier.model.Payment;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment updatePayment(Integer paymentId, Payment payment);
    void deletePayment(Integer paymentId);
    Payment getPaymentById(Integer paymentId);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByPacket(Integer packetId);
    List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate);
    List<Payment> getPaymentsByStatus(String status);
    List<Payment> getPaymentsByMethod(String method);
    List<Payment> getPaymentsWithDiscount();
    List<Payment> getPendingPayments();
} 