package com.spring.kurswork_beautysalon_web.controller;

import com.spring.kurswork_beautysalon_web.entity.BookedRecords;
import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.repository.BookedRecordsRepository;
import com.spring.kurswork_beautysalon_web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userPage(@AuthenticationPrincipal User user,
                           Model model) {
        model.addAttribute("user", user);
        var fullUser = userRepository.getOne(user.getId());
        var records = new ArrayList<BookedRecords>();
        for (var tick : fullUser.getBookedRecords()) {
            tick.setUser(user);
            records.add(tick);
        }
        model.addAttribute("bookedRecords", records);
        return "user/index";
    }
}
