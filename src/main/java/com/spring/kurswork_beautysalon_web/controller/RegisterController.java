package com.spring.kurswork_beautysalon_web.controller;

import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (user == null) {
            System.out.println("NULL");
        } else {
            System.out.println(user.toString());
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            System.out.println(errors.toString());
            return "register";
        }
        assert user != null;
        var result = userService.addUser(user);
        if (result) {
            return "redirect:/";
        }
        model.addAttribute("userNameError", "Пользователь уже существует!");
        return "register";
    }
}