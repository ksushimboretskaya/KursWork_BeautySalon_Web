package com.spring.kurswork_beautysalon_web.controller.api.admin;

import com.spring.kurswork_beautysalon_web.controller.ControllerUtils;
import com.spring.kurswork_beautysalon_web.entity.Role;
import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.entity.api.ErrorInfo;
import com.spring.kurswork_beautysalon_web.repository.RoleRepository;
import com.spring.kurswork_beautysalon_web.repository.UserRepository;
import com.spring.kurswork_beautysalon_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UsersApiAdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @GetMapping()
    public @ResponseBody
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping()
    public @ResponseBody
    Object addUser(HttpServletResponse response, @RequestBody User user) {
        var newUser = new User();
        newUser.setActive(user.getActive());
        newUser.setEmail(user.getEmail());
        newUser.setLogin(user.getLogin());
        newUser.setFullName(user.getFullName());
        List<Role> role1s = new ArrayList<>();
        for (var role : user.getRoles()) {
            var trueRole = roleRepository.getOne(role.getId());
            role1s.add(trueRole);
        }
        newUser.setRoles(role1s);
        newUser.setPassword(user.getPassword());
        var k = userService.addUser(newUser);
        response.setStatus(200);
        return new ErrorInfo(200, "Пользователь успешно добавлен.");
    }

    @GetMapping("{id}")
    public @ResponseBody
    Object getUser(HttpServletResponse response, @PathVariable Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный пользователь не найден");
        }
    }

    @PostMapping("{id}")
    public Object saveUser(HttpServletResponse response, @RequestBody @Valid User user,
                           BindingResult bindingResult) {
        if (user == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(user);
        }
        if (!bindingResult.hasErrors()) {
            var correctUser = userRepository.findById(user.getId()).get();
            correctUser.setActive(user.getActive());
            correctUser.setLogin(user.getLogin());
            correctUser.setEmail(user.getEmail());
            correctUser.setFullName(user.getFullName());
            correctUser.setPassword(user.getPassword());
            List<Role> role1s = new ArrayList<>();
            for (var role : user.getRoles()) {
                var trueRole = roleRepository.getOne(role.getId());
                role1s.add(trueRole);
            }
            correctUser.setRoles(role1s);
            userRepository.save(correctUser);
            response.setStatus(200);
            return new ErrorInfo(200, "Пользователь успешно обновлен");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    Object removeUser(HttpServletResponse response, @PathVariable Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Пользователь успешно удален");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный пользователь не найден");
        }
    }
}