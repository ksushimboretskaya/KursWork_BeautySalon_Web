package com.spring.kurswork_beautysalon_web.repository;

import com.spring.kurswork_beautysalon_web.entity.BookedRecords;
import com.spring.kurswork_beautysalon_web.entity.FreeRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedRecordsRepository extends JpaRepository<BookedRecords, Long> {
    List<BookedRecords> findBookedRecordsByFreeRecords(FreeRecords freeRecords);
}