package com.andikscript.simpleusermongodb.repository.h2;

import com.andikscript.simpleusermongodb.model.postgresql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
