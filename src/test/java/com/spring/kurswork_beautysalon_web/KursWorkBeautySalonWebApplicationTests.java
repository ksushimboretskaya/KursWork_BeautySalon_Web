package com.spring.kurswork_beautysalon_web;

import com.spring.kurswork_beautysalon_web.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
class KursWorkBeautySalonWebApplicationTests {
    @Test()
    @Description("Сравниваем два разных объекта типа \"User\".")
    public void testAddUser() {
        User user = new User();
        user.setId(2L);
        User user2 = new User();
        user2.setId(3L);
        Assertions.assertNotEquals(user, user2);
    }

    @Test
    @Description("Добавляем два разных объекта типа \"Employee\".")
    public void testAddEmployee() {
        Employee employee = new Employee();
        employee.setFullName("Шимборецкая Ксения Владимирова");
        Employee employee1 = new Employee();
        employee1.setFullName("Царун Янина Дмитриевна");
        Assertions.assertNotEquals(employee1, employee);
    }


    @Test
    @Description("Добавляем два разных объекта типа \"Services\".")
    public void testAddServices() {
        Services services = new Services();
        services.setDuration("5 часов");
        Services serv = new Services();
        serv.setDuration(" 3 часа");
        Assertions.assertNotEquals(serv, services);
    }

    @Test
    @Description("Добавляем два разных объекта типа \"BookedRecords\".")
    public void testAddBookedRecords() {
        BookedRecords bookedRecords = new BookedRecords();
        bookedRecords.setId(5L);
        BookedRecords bookedRecords1 = new BookedRecords();
        bookedRecords1.setId(3L);
        Assertions.assertNotEquals(bookedRecords, bookedRecords1);
    }

    @Test
    @Description("Добавляем два разных объекта типа \"Role\".")
    public void testAddRole() {
        Role role = new Role();
        role.setName("ADMIN");
        Role role1 = new Role();
        role1.setName("USER");
        Assertions.assertNotEquals(role, role1);
    }

    @Test
    @Description("Добавляем два разных объекта типа \"FreeRecords\".")
    public void testtAddFreeREcords() {
        FreeRecords freeRecords = new FreeRecords();
        freeRecords.setId(3L);
        FreeRecords freeRecords1 = new FreeRecords();
        freeRecords1.setId(5L);
        Assertions.assertNotEquals(freeRecords1, freeRecords);
    }
}