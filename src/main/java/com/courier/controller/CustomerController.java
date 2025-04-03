package com.courier.controller;

import com.courier.model.Customer;
import com.courier.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Management", description = "APIs for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Creates a new customer with the provided information")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{customerId}")
    @Operation(summary = "Update customer", description = "Updates an existing customer's information")
    public ResponseEntity<Customer> updateCustomer(
            @Parameter(description = "Customer ID") @PathVariable Integer customerId,
            @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, customer));
    }

    @DeleteMapping("/{customerId}")
    @Operation(summary = "Delete customer", description = "Deletes a customer by ID")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "Customer ID") @PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get customer by ID", description = "Retrieves a customer by their ID")
    public ResponseEntity<Customer> getCustomerById(
            @Parameter(description = "Customer ID") @PathVariable Integer customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieves a list of all customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    @Operation(summary = "Search customers", description = "Searches customers by keyword in name or phone")
    public ResponseEntity<List<Customer>> searchCustomers(
            @Parameter(description = "Search keyword") @RequestParam String keyword) {
        return ResponseEntity.ok(customerService.searchCustomers(keyword));
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Get customers by city", description = "Retrieves customers from a specific city")
    public ResponseEntity<List<Customer>> getCustomersByCity(
            @Parameter(description = "City name") @PathVariable String city) {
        return ResponseEntity.ok(customerService.getCustomersByCity(city));
    }
} 