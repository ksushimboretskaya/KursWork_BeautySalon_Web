package com.spring.kurswork_beautysalon_web.controller;

import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.repository.EmployeeRepository;
import com.spring.kurswork_beautysalon_web.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            var fakeUser = new User();
            fakeUser.setId(-1L);
            fakeUser.setLogin("null");
            model.addAttribute("user", fakeUser);
        }
        model.addAttribute("services", servicesRepository.findAll());
        model.addAttribute("employee", employeeRepository.findAll());
        model.addAttribute("dateNow", LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "hello";
    }
}