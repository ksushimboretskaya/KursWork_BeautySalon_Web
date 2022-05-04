package com.spring.kurswork_beautysalon_web;

import com.spring.kurswork_beautysalon_web.entity.BookedRecords;
import com.spring.kurswork_beautysalon_web.repository.BookedRecordsRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.List;

@SpringBootTest
public class BookedRecordsTest {
    @Autowired
    private BookedRecordsRepository bookedRecordsRepository;

    @Test
    @Description("")
    public void checkFindAllBookedRecords() {
        List<BookedRecords> bookedRecords = bookedRecordsRepository.findAll();
        Assertions.assertNotNull(bookedRecords);
    }
}