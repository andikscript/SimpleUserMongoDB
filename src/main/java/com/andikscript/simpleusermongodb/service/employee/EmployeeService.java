package com.andikscript.simpleusermongodb.service.employee;

import com.andikscript.simpleusermongodb.handling.UserNotFound;
import com.andikscript.simpleusermongodb.model.postgresql.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    void createEmployee(Employee employee);

    List<Employee> getAllEmployee();

    void updateEmployee(Employee employee, UUID id) throws UserNotFound;

    void deleteEmployee(UUID id) throws UserNotFound;
}
