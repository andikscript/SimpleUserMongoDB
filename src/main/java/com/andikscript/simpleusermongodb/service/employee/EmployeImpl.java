package com.andikscript.simpleusermongodb.service.employee;

import com.andikscript.simpleusermongodb.model.h2.Employee;
import com.andikscript.simpleusermongodb.repository.h2.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
