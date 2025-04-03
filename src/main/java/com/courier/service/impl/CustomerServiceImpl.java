package com.courier.service.impl;

import com.courier.model.Customer;
import com.courier.service.CustomerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final JdbcTemplate jdbcTemplate;

    public CustomerServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        String sql = "INSERT INTO Customers (FirstName, LastName, Phone, Email, Address, PostalCode, NationalID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getPostalCode());
            ps.setString(7, customer.getNationalId());
            return ps;
        }, keyHolder);

        customer.setCustomerId(keyHolder.getKey().intValue());
        return customer;
    }

    @Override
    @Transactional
    public Customer updateCustomer(Integer customerId, Customer customer) {
        String sql = "UPDATE Customers SET FirstName = ?, LastName = ?, Phone = ?, Email = ?, " +
                    "Address = ?, PostalCode = ? WHERE CustomerID = ?";
        
        jdbcTemplate.update(sql,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPostalCode(),
                customerId);

        return getCustomerById(customerId);
    }

    @Override
    @Transactional
    public void deleteCustomer(Integer customerId) {
        String sql = "DELETE FROM Customers WHERE CustomerID = ?";
        jdbcTemplate.update(sql, customerId);
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        String sql = "SELECT * FROM Customers WHERE CustomerID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM Customers";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        String sql = "SELECT * FROM Customers WHERE FirstName LIKE ? OR LastName LIKE ? OR Phone LIKE ?";
        String pattern = "%" + keyword + "%";
        return jdbcTemplate.query(sql, 
            new BeanPropertyRowMapper<>(Customer.class), 
            pattern, pattern, pattern);
    }

    @Override
    public List<Customer> getCustomersByCity(String city) {
        String sql = "SELECT * FROM Customers WHERE Address LIKE ?";
        return jdbcTemplate.query(sql, 
            new BeanPropertyRowMapper<>(Customer.class), 
            "%" + city + "%");
    }
} 