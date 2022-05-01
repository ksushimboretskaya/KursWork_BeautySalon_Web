package com.spring.kurswork_beautysalon_web.controller.api.admin;

import com.spring.kurswork_beautysalon_web.controller.ControllerUtils;
import com.spring.kurswork_beautysalon_web.entity.BookedRecords;
import com.spring.kurswork_beautysalon_web.entity.api.ErrorInfo;
import com.spring.kurswork_beautysalon_web.repository.BookedRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/bookedRecords")
public class BookedRecordsApiAdminController {
    @Autowired
    private BookedRecordsRepository bookedRecordsRepository;

    @PutMapping()
    public @ResponseBody
    Object addBTicket(HttpServletResponse response, @RequestBody BookedRecords bookedRecords) {
        var newBookedRecords = new BookedRecords();
        newBookedRecords.setFreeRecords(bookedRecords.getFreeRecords());
        newBookedRecords.setUser(bookedRecords.getUser());
        var k = bookedRecordsRepository.save(newBookedRecords);
        response.setStatus(200);
        return new ErrorInfo(200, "Забронированная запись успешно добавлена с id " + k.getId() + " .");
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    Object removeBTicket(HttpServletResponse response, @PathVariable Long id) {
        var bookedRecords = bookedRecordsRepository.findById(id);
        if (bookedRecords.isPresent()) {
            bookedRecordsRepository.delete(bookedRecords.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Забронированное окошко успешно удалено.");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Даннок забронированное окошко не найдено.");
        }
    }

    @GetMapping()
    public @ResponseBody
    List<BookedRecords> getBTickets() {
        return bookedRecordsRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody
    Object getBTicket(HttpServletResponse response, @PathVariable Long id) {
        var bookedRecord = bookedRecordsRepository.findById(id);
        if (bookedRecord.isPresent()) {
            return bookedRecord.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Даннок забронированное окошко не найдено.");
        }
    }

    @PostMapping("{id}")
    public Object saveBTicket(HttpServletResponse response, @RequestBody @Valid BookedRecords bookedRecords,
                              BindingResult bindingResult) {
        if (bookedRecords == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос.");
        } else {
            System.out.println(bookedRecords.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = bookedRecordsRepository.findById(bookedRecords.getId()).get();
            correct.setFreeRecords(bookedRecords.getFreeRecords());
            correct.setUser(bookedRecords.getUser());
            bookedRecordsRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Забронированное окошко успешно обновлено.");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }
}
