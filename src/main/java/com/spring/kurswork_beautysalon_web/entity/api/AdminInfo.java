package com.spring.kurswork_beautysalon_web.entity.api;

public class AdminInfo {
    private long roles;
    private long users;
    private long services;
    private long employee;
    private long bookedRecords;
    private long freeRecords;

    public AdminInfo(long services, long employee, long bookedRecords, long freeRecords, long roles, long users) {
        this.services = services;
        this.employee = employee;
        this.bookedRecords = bookedRecords;
        this.freeRecords = freeRecords;
        this.roles = roles;
        this.users = users;
    }

    public long getServices() {
        return services;
    }

    public void setServices(long services) {
        this.services = services;
    }

    public long getEmployee() {
        return employee;
    }

    public void setEmployee(long employee) {
        this.employee = employee;
    }

    public long getBookedRecords() {
        return bookedRecords;
    }

    public void setBookedRecords(long bookedRecords) {
        this.bookedRecords = bookedRecords;
    }

    public long getFreeRecords() {
        return freeRecords;
    }

    public void setFreeRecords(long freeRecords) {
        this.freeRecords = freeRecords;
    }

    public long getRoles() {
        return roles;
    }

    public void setRoles(long roles) {
        this.roles = roles;
    }

    public long getUsers() {
        return users;
    }

    public void setUsers(long users) {
        this.users = users;
    }
}