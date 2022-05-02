package com.spring.kurswork_beautysalon_web;

import com.spring.kurswork_beautysalon_web.entity.Role;
import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FindAllUsersTest {
    @Autowired
    private UserRepository userRepository;

    @Test()
    @Description("Проверка на получение данных из базы данных.")
    public void verifyUsers() {
        List<User> usersList = new ArrayList<>();
        usersList.add(new User(1L, "tes1t", "tes1t", "test1", "test1", true));
        usersList.add(new User(2L, "test2", "test2", "test2", "test2", true));
        usersList.add(new User(3L, "test3", "test3", "test3", "test3", true));
        List<User> usersListFromDataBase = userRepository.findAll();
        Assertions.assertNotNull(usersListFromDataBase);
        Assertions.assertNotEquals(usersList, usersListFromDataBase);

    }
}
