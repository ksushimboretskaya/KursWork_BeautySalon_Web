package com.spring.kurswork_beautysalon_web.repository;

import com.spring.kurswork_beautysalon_web.entity.Employee;
import com.spring.kurswork_beautysalon_web.entity.FreeRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FreeRecordsRepository extends JpaRepository<FreeRecords, Long> {
    List<FreeRecords> findByEmployeeAndDate(Employee employee, LocalDate date);
}
