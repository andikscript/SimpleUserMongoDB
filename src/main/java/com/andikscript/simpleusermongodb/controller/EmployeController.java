package com.andikscript.simpleusermongodb.controller;

import com.andikscript.simpleusermongodb.handling.SoldOutSalary;
import com.andikscript.simpleusermongodb.handling.UserNotFound;
import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.model.postgresql.Employee;
import com.andikscript.simpleusermongodb.service.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeController {
    private final EmployeeService employeeService;

    public EmployeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
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

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employee,
                                            @PathVariable(value = "id") UUID id) throws UserNotFound, SoldOutSalary {
        employeeService.updateEmployee(employee, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("Success update employee"));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") UUID id) throws UserNotFound {
        employeeService.deleteEmployee(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("Success delete employee"));
    }

    @GetMapping(value = "/employees/{name}")
    public ResponseEntity<?> getAllEmployeeByNama(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.findNama(name));
    }

    @GetMapping(value = "/employees/{name}/{gaji}")
    public ResponseEntity<?> getAllEmployeeByNamaGaji(@PathVariable(value = "name") String name,
                                                      @PathVariable(value = "gaji") Integer gaji) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.findNamaGaji(name, gaji));
    }
}
