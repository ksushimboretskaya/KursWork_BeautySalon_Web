package com.spring.kurswork_beautysalon_web;

import com.spring.kurswork_beautysalon_web.entity.Employee;
import com.spring.kurswork_beautysalon_web.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.List;

@SpringBootTest
public class EmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Description("Проверяем, достаются ли данные из бд")
    public void checkFindAllEmployee() {
        List<Employee> employeeListFromDataBase = employeeRepository.findAll();
        Assertions.assertNotNull(employeeListFromDataBase);
    }
}