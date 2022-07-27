package com.andikscript.simpleusermongodb.controller;

import com.andikscript.simpleusermongodb.handling.FailedValueBody;
import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.model.h2.Employee;
import com.andikscript.simpleusermongodb.service.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeController {
    private final EmployeeService employeeService;

    public EmployeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) throws FailedValueBody {
        employeeService.createEmployee(employee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("Successfully create employee"));
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<?> getAllEmployee() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.getAllEmployee());
    }
}
