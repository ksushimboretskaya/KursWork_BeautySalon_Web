package com.spring.kurswork_beautysalon_web.controller;

import com.spring.kurswork_beautysalon_web.entity.api.AdminInfo;
import com.spring.kurswork_beautysalon_web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private FreeRecordsRepository freeRecordsRepository;
    @Autowired
    private BookedRecordsRepository bookedRecordsRepository;

    @GetMapping
    public String adminPage(Model model) {
        var services = servicesRepository.count();
        var bookedRecords = bookedRecordsRepository.count();
        var employee = employeeRepository.count();
        var freeRecords = freeRecordsRepository.count();
        var roles = roleRepository.count();
        var users = userRepository.count();
        model.addAttribute("info", new AdminInfo(services, employee, bookedRecords, freeRecords, roles, users));
        return "admin/index";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @GetMapping("/roles")
    public String rolesPage(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/roles";
    }

    @GetMapping("/services")
    public String servicesPage(Model model) {
        model.addAttribute("services", servicesRepository.findAll());
        return "admin/services";
    }

    @GetMapping("/employee")
    public String employeePage(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("services", servicesRepository.findAll());
        return "admin/employee";
    }

    @GetMapping("/bookedRecords")
    public String bookedRecordsPage(Model model) {
        model.addAttribute("bookedRecords", bookedRecordsRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("freeRecords", freeRecordsRepository.findAll());
        return "admin/bookedRecords";
    }

    @GetMapping("/freeRecords")
    public String freeRecordsPage(Model model) {
        model.addAttribute("freeRecords", freeRecordsRepository.findAll());
        model.addAttribute("services", servicesRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "admin/freeRecords";
    }
}