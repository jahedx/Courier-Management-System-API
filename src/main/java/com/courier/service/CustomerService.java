package com.courier.service;

import com.courier.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Integer customerId, Customer customer);
    void deleteCustomer(Integer customerId);
    Customer getCustomerById(Integer customerId);
    List<Customer> getAllCustomers();
    List<Customer> searchCustomers(String keyword);
    List<Customer> getCustomersByCity(String city);
} 