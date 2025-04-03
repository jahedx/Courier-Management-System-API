package com.courier.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String postalCode;
    private String nationalId;
} 