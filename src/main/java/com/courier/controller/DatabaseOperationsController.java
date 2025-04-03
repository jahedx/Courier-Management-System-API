package com.courier.controller;

import com.courier.model.Customer;
import com.courier.model.Packet;
import com.courier.model.Payment;
import com.courier.service.DatabaseOperationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/db-operations")
@Tag(name = "Database Operations", description = "APIs using stored procedures, functions, and views")
public class DatabaseOperationsController {

    private final DatabaseOperationsService dbOperationsService;

    public DatabaseOperationsController(DatabaseOperationsService dbOperationsService) {
        this.dbOperationsService = dbOperationsService;
    }

    // Stored Procedure Endpoints
    @PostMapping("/customers/sp")
    @Operation(summary = "Create customer using stored procedure", description = "Creates a new customer using sp_CreateCustomer")
    public ResponseEntity<Void> createCustomerUsingSP(@Valid @RequestBody Customer customer) {
        dbOperationsService.createCustomerUsingSP(customer);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/customers/{customerId}/sp")
    @Operation(summary = "Update customer using stored procedure", description = "Updates a customer using sp_UpdateCustomer")
    public ResponseEntity<Void> updateCustomerUsingSP(
            @Parameter(description = "Customer ID") @PathVariable Integer customerId,
            @Valid @RequestBody Customer customer) {
        dbOperationsService.updateCustomerUsingSP(customerId, customer);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/packets/sp")
    @Operation(summary = "Add packet using stored procedure", description = "Creates a new packet using sp_AddPacket")
    public ResponseEntity<Void> addPacketUsingSP(@Valid @RequestBody Packet packet) {
        dbOperationsService.addPacketUsingSP(packet);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/packets/{packetId}/sp")
    @Operation(summary = "Delete packet using stored procedure", description = "Deletes a packet using sp_DeletePacket")
    public ResponseEntity<Void> deletePacketUsingSP(
            @Parameter(description = "Packet ID") @PathVariable Integer packetId) {
        dbOperationsService.deletePacketUsingSP(packetId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/payments/sp")
    @Operation(summary = "Process payment using stored procedure", description = "Processes a payment using sp_ProcessPayment")
    public ResponseEntity<Void> processPaymentUsingSP(@Valid @RequestBody Payment payment) {
        dbOperationsService.processPaymentUsingSP(payment);
        return ResponseEntity.ok().build();
    }

    // Function Endpoints
    @GetMapping("/customers/{customerId}/total-payments")
    @Operation(summary = "Get total payments for customer", description = "Uses GetTotalPaymentsForCustomer function")
    public ResponseEntity<Map<String, Object>> getTotalPaymentsForCustomer(
            @Parameter(description = "Customer ID") @PathVariable Integer customerId) {
        return ResponseEntity.ok(dbOperationsService.getTotalPaymentsForCustomer(customerId));
    }

    @GetMapping("/packets/{packetId}/status")
    @Operation(summary = "Get packet status", description = "Uses GetPacketStatusByPacketID function")
    public ResponseEntity<Map<String, Object>> getPacketStatusByPacketId(
            @Parameter(description = "Packet ID") @PathVariable Integer packetId) {
        return ResponseEntity.ok(dbOperationsService.getPacketStatusByPacketId(packetId));
    }

    @GetMapping("/employees/{employeeId}/workload")
    @Operation(summary = "Get employee workload", description = "Uses GetEmployeeWorkloadByID function")
    public ResponseEntity<Map<String, Object>> getEmployeeWorkloadById(
            @Parameter(description = "Employee ID") @PathVariable Integer employeeId) {
        return ResponseEntity.ok(dbOperationsService.getEmployeeWorkloadById(employeeId));
    }

    // View Endpoints
    @GetMapping("/customer-shipments")
    @Operation(summary = "Get customer shipments", description = "Uses CustomerShipments view")
    public ResponseEntity<List<Map<String, Object>>> getCustomerShipments() {
        return ResponseEntity.ok(dbOperationsService.getCustomerShipments());
    }

    @GetMapping("/customer-total-spent")
    @Operation(summary = "Get customer total spent", description = "Uses CustomerTotalSpent view")
    public ResponseEntity<List<Map<String, Object>>> getCustomerTotalSpent() {
        return ResponseEntity.ok(dbOperationsService.getCustomerTotalSpent());
    }

    @GetMapping("/employee-workload")
    @Operation(summary = "Get employee workload", description = "Uses EmployeeWorkload view")
    public ResponseEntity<List<Map<String, Object>>> getEmployeeWorkload() {
        return ResponseEntity.ok(dbOperationsService.getEmployeeWorkload());
    }

    @GetMapping("/recent-deliveries")
    @Operation(summary = "Get recent deliveries", description = "Uses RecentDeliveries view")
    public ResponseEntity<List<Map<String, Object>>> getRecentDeliveries() {
        return ResponseEntity.ok(dbOperationsService.getRecentDeliveries());
    }

    @GetMapping("/shipment-status-summary")
    @Operation(summary = "Get shipment status summary", description = "Uses ShipmentStatusSummary view")
    public ResponseEntity<List<Map<String, Object>>> getShipmentStatusSummary() {
        return ResponseEntity.ok(dbOperationsService.getShipmentStatusSummary());
    }

    @GetMapping("/top-paying-customers")
    @Operation(summary = "Get top paying customers", description = "Uses TopPayingCustomers view")
    public ResponseEntity<List<Map<String, Object>>> getTopPayingCustomers() {
        return ResponseEntity.ok(dbOperationsService.getTopPayingCustomers());
    }
}