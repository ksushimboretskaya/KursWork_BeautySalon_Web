package com.spring.kurswork_beautysalon_web;

import com.spring.kurswork_beautysalon_web.entity.FreeRecords;
import com.spring.kurswork_beautysalon_web.repository.FreeRecordsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.List;

@SpringBootTest
public class FreeRecordsTest {
    @Autowired
    private FreeRecordsRepository freeRecordsRepository;

    @Test
    @Description("ПРоверяем, достаются ли данные из бд.")
    public void checkFindAllFreeRecords() {
        List<FreeRecords> freeRecordsListFromDataBase = freeRecordsRepository.findAll();
        Assertions.assertNotNull(freeRecordsListFromDataBase);
    }
}