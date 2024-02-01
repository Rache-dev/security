package com.alibou.security.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void onBoard(EmployeeRequest request){
        var employee = Employee.builder()
                .fullName(request.getFullName())
                .contactInfo(request.getContactInfo())
                .position(request.getPosition())
                .department(request.getDepartment())
                .status(request.getStatus())
                .build();
        employeeRepository.save(employee);
    }

    public void

}
