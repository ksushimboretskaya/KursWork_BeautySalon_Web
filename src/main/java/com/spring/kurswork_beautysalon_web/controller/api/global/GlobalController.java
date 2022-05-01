package com.spring.kurswork_beautysalon_web.controller.api.global;

import com.spring.kurswork_beautysalon_web.entity.BookedRecords;
import com.spring.kurswork_beautysalon_web.entity.FreeRecords;
import com.spring.kurswork_beautysalon_web.entity.Services;
import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.entity.api.ErrorInfo;
import com.spring.kurswork_beautysalon_web.entity.api.RecordsInfo;
import com.spring.kurswork_beautysalon_web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
public class GlobalController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private FreeRecordsRepository freeRecordsRepository;
    @Autowired
    private BookedRecordsRepository bookedRecordsRepository;

    @GetMapping("freeRecords")
    public @ResponseBody
    Object getTickets( @RequestParam long services,
                      @RequestParam long employee,
                      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        var today = LocalDate.now();
        if (today.isAfter(date)) {
            return new ErrorInfo(400, "Поиск возможен только на текущую или будущую дату");
        }
        else {
            List<RecordsInfo> result = new ArrayList<>();
            var servicesSearch = servicesRepository.findById(services).get();
            var employeeSearch = employeeRepository.findById(employee).get();
            System.out.println("Нужно найти: " + servicesSearch.getServicesName() + " -> " + employeeSearch.getFullName());
            var booked = bookedRecordsRepository.findAll()
                    .stream()
                    .map(book -> book.getFreeRecords().getDate())
                    .collect(Collectors.toList());
            var resultList = freeRecordsRepository
                    .findByEmployeeAndDate(employeeSearch, date)
                    .stream()
                    .filter(res -> !booked.contains(date))
                    .collect(Collectors.toList());
            var servicesList = resultList.stream()
                    .map(serv -> serv.getEmployee().getServices().getServicesName())
                    .collect(Collectors.toList());

            for (var el : resultList) {
                var freeRecordsList = new ArrayList<FreeRecords>();
                freeRecordsList.add(el);
                System.out.println("------------------------------");
                System.out.println(servicesSearch.getServicesName() + " -> " + el.getEmployee().getFullName());
                if (servicesList.contains(servicesSearch.getServicesName())) {
                    System.out.println("Найдено!!!");
                    var recordsInfo = new RecordsInfo(freeRecordsList);
                    result.add(recordsInfo);
                    break;
                }
                System.out.println("------------------------------");
            }
            return result;
        }
    }

    @GetMapping("records/buy") public @ResponseBody
    Object buyTicket(HttpServletResponse response, @RequestParam long id, @AuthenticationPrincipal User user) {
        if (user == null || user.getId() == null) {
            response.setStatus(400);
            return new ErrorInfo(400,"Не удалось идентифицировать пользователя");
        }
        var ticket = new BookedRecords();
        var rec = freeRecordsRepository.findById(id).get();
        ticket.setFreeRecords(rec);
        ticket.setUser(user);
        bookedRecordsRepository.save(ticket);
        return new ErrorInfo(200,"Вы записались!");
    }

    @GetMapping("records/buys") public @ResponseBody
    Object buyTicket(HttpServletResponse response, @RequestParam long[] ids, @AuthenticationPrincipal User user) {
        if (user == null || user.getId() == null) {
            response.setStatus(400);
            return new ErrorInfo(400,"Не удалось идентифицировать пользователя");
        }
        for (var id : ids) {
            var ticket = new BookedRecords();
            var rec = freeRecordsRepository.getOne(id);
            ticket.setFreeRecords(rec);
            ticket.setUser(user);
            bookedRecordsRepository.save(ticket);
        }
        return new ErrorInfo(200,"Билеты успешно куплены");
    }
}