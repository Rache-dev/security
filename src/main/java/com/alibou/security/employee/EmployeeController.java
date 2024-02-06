package com.alibou.security.employee;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/employee")
@RequiredArgsConstructor

public class EmployeeController {
   private final EmployeeService employeeService;

    @PostMapping("/onBoard")
    public EntityResponse onBoard(@RequestBody Employee employee){

        return employeeService.onBoard(employee);
    }
}
