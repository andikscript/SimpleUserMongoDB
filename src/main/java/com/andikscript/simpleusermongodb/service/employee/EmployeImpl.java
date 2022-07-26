package com.andikscript.simpleusermongodb.service.employee;

import com.andikscript.simpleusermongodb.handling.SoldOutSalary;
import com.andikscript.simpleusermongodb.handling.UserNotFound;
import com.andikscript.simpleusermongodb.model.postgresql.Employee;
import com.andikscript.simpleusermongodb.repository.h2.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(Employee employee)  {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void updateEmployee(Employee employee, UUID id) throws UserNotFound, SoldOutSalary {
        Optional<Employee> getEmployee1 = employeeRepository.findById(id);

        if (!getEmployee1.isPresent()) {
            throw new UserNotFound();
        }

        employee.setId(id);
        if (getEmployee1.get().getGaji() - employee.getGaji() <= 0) {
            throw new SoldOutSalary();
        }
        employee.setGaji(getEmployee1.get().getGaji() - employee.getGaji());
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(UUID id) throws UserNotFound {
        Optional<Employee> getEmployee = employeeRepository.findById(id);

        if (!getEmployee.isPresent()) {
            throw new UserNotFound();
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findNama(String name) {
        return employeeRepository.findNama(name);
    }

    @Override
    public List<Employee> findNamaGaji(String name, Integer gaji) {
        return employeeRepository.findNamaGaji(name, gaji);
    }
}
