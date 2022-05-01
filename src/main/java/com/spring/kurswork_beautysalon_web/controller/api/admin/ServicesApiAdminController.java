package com.spring.kurswork_beautysalon_web.controller.api.admin;

import com.spring.kurswork_beautysalon_web.controller.ControllerUtils;
import com.spring.kurswork_beautysalon_web.entity.Services;
import com.spring.kurswork_beautysalon_web.entity.api.ErrorInfo;
import com.spring.kurswork_beautysalon_web.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/services")
public class ServicesApiAdminController {
    @Autowired
    private ServicesRepository servicesRepository;

    @PutMapping()
    public @ResponseBody
    Object addService(HttpServletResponse response, @RequestBody Services services) {
        if (services.getServicesName() == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Не указано название услуги.");
        }
        var newServices = new Services();
        newServices.setServicesName(services.getServicesName());
        newServices.setServicesPrice(services.getServicesPrice());
        newServices.setDuration(services.getDuration());
        var k = servicesRepository.save(newServices);
        response.setStatus(200);
        return new ErrorInfo(200, "Услуга успешно добавлена с id " + k.getId() + " .");
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    Object removeService(HttpServletResponse response, @PathVariable Long id) {
        var services = servicesRepository.findById(id);
        if (services.isPresent()) {
            servicesRepository.delete(services.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Услуга успешно удалена.");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данная услуга не найдена.");
        }
    }

    @GetMapping()
    public @ResponseBody
    List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody
    Object getService(HttpServletResponse response, @PathVariable Long id) {
        var services = servicesRepository.findById(id);
        if (services.isPresent()) {
            return services.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данная услуга не найдена.");
        }
    }

    @PostMapping("{id}")
    public Object saveService(HttpServletResponse response, @RequestBody @Valid Services services, BindingResult bindingResult) {
        if (services == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос.");
        } else {
            System.out.println(services.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = servicesRepository.findById(services.getId()).get();
            correct.setServicesName(services.getServicesName());
            correct.setServicesPrice(services.getServicesPrice());
            correct.setDuration(services.getDuration());
            servicesRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Услуга успешно обновлена.");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }
}
