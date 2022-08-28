package com.andikscript.simpleusermongodb.repository.h2;

import com.andikscript.simpleusermongodb.model.postgresql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(value = "SELECT * FROM Employee u WHERE u.nama LIKE ?1%", nativeQuery = true)
    List<Employee> findNama(String nama);

    @Query(value = "SELECT * FROM Employee u WHERE u.nama LIKE ?1% AND u.gaji = ?2", nativeQuery = true)
    List<Employee> findNamaGaji(String nama, Integer gaji);
}
