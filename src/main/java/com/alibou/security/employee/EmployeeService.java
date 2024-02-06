package com.alibou.security.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional @Slf4j
public class EmployeeService {
   private final EmployeeRepository employeeRepository;

    public EntityResponse<Employee> onBoard(Employee employee) {
        EntityResponse response = new EntityResponse();

        Employee savedEmployee = employeeRepository.save(employee);
        response.setMessage("Employee created successfully");
        response.setStatusCode(HttpStatus.OK.value());
        response.setEntity(savedEmployee);

        return response;

//       try {
//           // Log the received employee data
//           log.info("Received employee data: {}", employee);
//
//           employeeRepository.flush();
//       }catch (Exception e){
//           log.error("Failed to create employee: {}", e.getMessage(), e);
//           response.setMessage("Failed to create employee: " + e.getMessage());
//           response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//       }


    }

}


//    public ResponseEntity<Employee> onBoard(Employee employee) {
//        Employee savedEmployee = employeeRepository.save(employee);
//        return ResponseEntity.status(HttpStatus.OK.value()).body(savedEmployee);

//    public EntityResponse onBoard(Employee employee){
//         var employee = Employee.builder()
//                .fullname(employee.getFullname())
//                .contactinfo(employee.getContactinfo())
//                .position(employee.getPosition())
//                .department(employee.getDepartment())
//                .status(employee.getStatus())
//                .build();
//        employeeRepository.save(employee);
//
//        return
//    }






//    public Employee editEmployee(EmployeeRequest request){
//        Long employeeId = request.getId();
//        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
//
//            Employee existingEmployee = optionalEmployee.get();
//            existingEmployee.setFullName(request.getFullName());
//            existingEmployee.setContactInfo(request.getContactInfo());
//            existingEmployee.setPosition(request.getPosition());
//            existingEmployee.setDepartment(request.getDepartment());
//            existingEmployee.setStatus(request.getStatus());
//            return employeeRepository.save(existingEmployee);
//
//    }
//    public void deleteEmployee(EmployeeRequest request){
//        Long employeeId = request.getId();
//        employeeRepository.deleteById(employeeId);
//    }

