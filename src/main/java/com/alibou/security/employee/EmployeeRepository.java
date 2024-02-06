package com.alibou.security.employee;

import com.alibou.security.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //List<Employee> findByStatus(String status);
}
