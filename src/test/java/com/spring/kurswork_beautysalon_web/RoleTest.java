package com.spring.kurswork_beautysalon_web;

import com.spring.kurswork_beautysalon_web.entity.Role;
import com.spring.kurswork_beautysalon_web.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class RoleTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Description("")
    public void checkFindAllRoles() {
        List<Role> roles = roleRepository.findAll();
        Assertions.assertNotNull(roles);
    }

    @Test
    @Description("Проверяем данные из бд")
    public void checkRolesFromDataBase() {
        List<String> rolesExpected = new ArrayList<>();
        rolesExpected.add("USER");
        rolesExpected.add("ADMIN");
        List<String> roleListFromDataBase = roleRepository.findAll()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        Collections.sort(rolesExpected);
        Collections.sort(roleListFromDataBase);
        Assertions.assertEquals(rolesExpected, roleListFromDataBase);
    }
}

