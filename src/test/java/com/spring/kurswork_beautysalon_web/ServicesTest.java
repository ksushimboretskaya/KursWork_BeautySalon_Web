package com.spring.kurswork_beautysalon_web;

import com.spring.kurswork_beautysalon_web.entity.Services;
import com.spring.kurswork_beautysalon_web.repository.ServicesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.List;

@SpringBootTest
public class ServicesTest {
    @Autowired
    private ServicesRepository servicesRepository;

    @Test
    @Description("Проверяем, достаются ли данные из базы данных.")
    public void checkFindAllServices() {
        List<Services> servicesListFromDataBase = servicesRepository.findAll();
        Assertions.assertNotNull(servicesListFromDataBase);
    }
}