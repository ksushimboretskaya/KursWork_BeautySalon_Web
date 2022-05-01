package com.spring.kurswork_beautysalon_web.controller.api.admin;

import com.spring.kurswork_beautysalon_web.controller.ControllerUtils;
import com.spring.kurswork_beautysalon_web.entity.Employee;
import com.spring.kurswork_beautysalon_web.entity.api.ErrorInfo;
import com.spring.kurswork_beautysalon_web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/employee")
public class EmployeeApiController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PutMapping()
    public @ResponseBody
    Object addEmployee(HttpServletResponse response, @RequestBody Employee employee) {
        var employeeNew = new Employee();
        employeeNew.setFullName(employee.getFullName());
        var k = employeeRepository.save(employeeNew);
        response.setStatus(200);
        return new ErrorInfo(200, "Сотрудник салона успешно добавлен с id " + k.getId() + " .");
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    Object removeEmployee(HttpServletResponse response, @PathVariable Long id) {
        var employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Сотрудник салона успешно удален.");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данного сотрудника не в штате. Проверьте данные.");
        }
    }

    @GetMapping()
    public @ResponseBody
    List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody
    Object getEmployee(HttpServletResponse response, @PathVariable Long id) {
        var employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данного сотрудника не в штате. Проверьте данные.");
        }
    }

    @PostMapping("{id}")
    public Object saveEmployee(HttpServletResponse response, @RequestBody @Valid Employee employee,
                               BindingResult bindingResult) {
        if (employee == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(employee.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = employeeRepository.findById(employee.getId()).get();
            correct.setFullName(employee.getFullName());
            employeeRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Данные о сотруднике успешно обновлены.");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }
}
