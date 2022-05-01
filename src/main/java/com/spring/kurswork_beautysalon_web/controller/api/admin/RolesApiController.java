package com.spring.kurswork_beautysalon_web.controller.api.admin;

import com.spring.kurswork_beautysalon_web.controller.ControllerUtils;
import com.spring.kurswork_beautysalon_web.entity.Role;
import com.spring.kurswork_beautysalon_web.entity.api.ErrorInfo;
import com.spring.kurswork_beautysalon_web.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/roles")
public class RolesApiController {
    @Autowired
    private RoleRepository roleRepository;

    @PutMapping()
    public @ResponseBody
    Object addRole(HttpServletResponse response, @RequestBody Role role) {
        var role1 = new Role();
        role1.setName(role.getName());
        var k = roleRepository.save(role1);
        response.setStatus(200);
        return new ErrorInfo(200, "Роль успешно добавлена с id " + k.getId());
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    Object removeRole(HttpServletResponse response, @PathVariable Long id) {
        var role = roleRepository.findById(id);
        if (role.isPresent()) {
            roleRepository.delete(role.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Роль успешно удалена");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данная роль не найдена");
        }
    }

    @GetMapping()
    public @ResponseBody
    List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody
    Object getRole(HttpServletResponse response, @PathVariable Long id) {
        var role = roleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данная роль не найдена");
        }
    }

    @PostMapping("{id}")
    public Object saveRole(HttpServletResponse response, @RequestBody @Valid Role role, BindingResult bindingResult) {
        if (role == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(role.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = roleRepository.findById(role.getId()).get();
            correct.setName(role.getName());
            roleRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Роль успешно обновлена");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }
}