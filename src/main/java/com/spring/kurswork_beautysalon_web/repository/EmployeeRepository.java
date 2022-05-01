package com.spring.kurswork_beautysalon_web.repository;

import com.spring.kurswork_beautysalon_web.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFullName(String fullName);
    Optional<Employee> findById(Long id);
}