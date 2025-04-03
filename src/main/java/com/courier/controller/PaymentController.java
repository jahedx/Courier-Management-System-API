package com.courier.controller;

import com.courier.model.Payment;
import com.courier.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment Management", description = "APIs for managing courier payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @Operation(summary = "Create a new payment", description = "Creates a new payment record")
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.createPayment(payment));
    }

    @PutMapping("/{paymentId}")
    @Operation(summary = "Update payment", description = "Updates an existing payment record")
    public ResponseEntity<Payment> updatePayment(
            @Parameter(description = "Payment ID") @PathVariable Integer paymentId,
            @Valid @RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.updatePayment(paymentId, payment));
    }

    @DeleteMapping("/{paymentId}")
    @Operation(summary = "Delete payment", description = "Deletes a payment record")
    public ResponseEntity<Void> deletePayment(
            @Parameter(description = "Payment ID") @PathVariable Integer paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Get payment by ID", description = "Retrieves a payment record by its ID")
    public ResponseEntity<Payment> getPaymentById(
            @Parameter(description = "Payment ID") @PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @GetMapping
    @Operation(summary = "Get all payments", description = "Retrieves a list of all payment records")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/packet/{packetId}")
    @Operation(summary = "Get payments by packet", description = "Retrieves all payments for a specific packet")
    public ResponseEntity<List<Payment>> getPaymentsByPacket(
            @Parameter(description = "Packet ID") @PathVariable Integer packetId) {
        return ResponseEntity.ok(paymentService.getPaymentsByPacket(packetId));
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get payments by date range", description = "Retrieves payments within a specific date range")
    public ResponseEntity<List<Payment>> getPaymentsByDateRange(
            @Parameter(description = "Start date") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(paymentService.getPaymentsByDateRange(startDate, endDate));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get payments by status", description = "Retrieves payments with a specific status")
    public ResponseEntity<List<Payment>> getPaymentsByStatus(
            @Parameter(description = "Payment status") @PathVariable String status) {
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(status));
    }

    @GetMapping("/method/{method}")
    @Operation(summary = "Get payments by method", description = "Retrieves payments made with a specific payment method")
    public ResponseEntity<List<Payment>> getPaymentsByMethod(
            @Parameter(description = "Payment method") @PathVariable String method) {
        return ResponseEntity.ok(paymentService.getPaymentsByMethod(method));
    }

    @GetMapping("/with-discount")
    @Operation(summary = "Get payments with discount", description = "Retrieves all payments that used a discount code")
    public ResponseEntity<List<Payment>> getPaymentsWithDiscount() {
        return ResponseEntity.ok(paymentService.getPaymentsWithDiscount());
    }

    @GetMapping("/pending")
    @Operation(summary = "Get pending payments", description = "Retrieves all payments with pending status")
    public ResponseEntity<List<Payment>> getPendingPayments() {
        return ResponseEntity.ok(paymentService.getPendingPayments());
    }
} 