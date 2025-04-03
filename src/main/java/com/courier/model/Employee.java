package com.courier.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String phone;
    private String position;
    private String branchLocation;
    private String nationalId;
    private String workShift;
} 