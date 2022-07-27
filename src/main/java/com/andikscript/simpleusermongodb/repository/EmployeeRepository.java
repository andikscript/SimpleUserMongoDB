package com.andikscript.simpleusermongodb.repository;

import com.andikscript.simpleusermongodb.model.h2.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
