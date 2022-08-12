package com.andikscript.simpleusermongodb.service.employee;

import com.andikscript.simpleusermongodb.model.postgresql.Employee;

import java.util.List;

public interface EmployeeService {

    void createEmployee(Employee employee);

    List<Employee> getAllEmployee();
}
