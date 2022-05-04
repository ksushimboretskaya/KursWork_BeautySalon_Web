package com.spring.kurswork_beautysalon_web.controller.api.admin;

import com.spring.kurswork_beautysalon_web.entity.api.AdminInfo;
import com.spring.kurswork_beautysalon_web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BookedRecordsRepository bookedRecordsRepository;
    @Autowired
    private FreeRecordsRepository freeRecordsRepository;

    @GetMapping("info")
    public @ResponseBody
    AdminInfo getInfo() {
        var services = servicesRepository.count();
        var employee = employeeRepository.count();
        var bookedRecords = bookedRecordsRepository.count();
        var freeRecords = freeRecordsRepository.count();
        var roles = roleRepository.count();
        var users = userRepository.count();
        return new AdminInfo(services, employee, bookedRecords, freeRecords, roles, users);
    }
}