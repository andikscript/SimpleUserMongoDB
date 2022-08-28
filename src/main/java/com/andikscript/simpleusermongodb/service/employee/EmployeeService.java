package com.andikscript.simpleusermongodb.service.employee;

import com.andikscript.simpleusermongodb.handling.SoldOutSalary;
import com.andikscript.simpleusermongodb.handling.UserNotFound;
import com.andikscript.simpleusermongodb.model.postgresql.Employee;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    @PreAuthorize("hasRole('ADMIN') or hasRole('ROOT')")
    void createEmployee(Employee employee);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('ROOT')")
    List<Employee> getAllEmployee();

    @PreAuthorize("hasRole('ROOT')")
    void updateEmployee(Employee employee, UUID id) throws UserNotFound, SoldOutSalary;

    @PreAuthorize("hasRole('ROOT')")
    void deleteEmployee(UUID id) throws UserNotFound;

    List<Employee> findNama(String name);

    List<Employee> findNamaGaji(String name, Integer gaji);
}
