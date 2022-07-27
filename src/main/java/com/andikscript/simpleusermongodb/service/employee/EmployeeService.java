package com.andikscript.simpleusermongodb.service.employee;

import com.andikscript.simpleusermongodb.model.h2.Employee;

import java.util.List;

public interface EmployeeService {

    void createEmployee(Employee employee);

    List<Employee> getAllEmployee();
}
