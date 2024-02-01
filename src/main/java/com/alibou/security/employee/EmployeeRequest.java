package com.alibou.security.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String fullName;
    private String contactInfo;
    private String position;
    private String department;
    private String status;
}
