package com.spring.kurswork_beautysalon_web.entity.api;

import com.spring.kurswork_beautysalon_web.entity.FreeRecords;
import com.spring.kurswork_beautysalon_web.entity.Services;

import java.util.ArrayList;
import java.util.List;

public class RecordsInfo {
    private List<FreeRecords> freeRecords = new ArrayList<>();

    public RecordsInfo() {
    }

    public RecordsInfo(List<FreeRecords> freeRecords) {
        this.freeRecords = freeRecords;
    }

    public List<FreeRecords> getFreeRecords() {
        return freeRecords;
    }

    public void setFreeRecords(List<FreeRecords> freeRecords) {
        this.freeRecords = freeRecords;
    }
}