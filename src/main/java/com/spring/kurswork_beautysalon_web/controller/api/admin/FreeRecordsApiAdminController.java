package com.spring.kurswork_beautysalon_web.controller.api.admin;

import com.spring.kurswork_beautysalon_web.controller.ControllerUtils;
import com.spring.kurswork_beautysalon_web.entity.FreeRecords;
import com.spring.kurswork_beautysalon_web.entity.api.ErrorInfo;
import com.spring.kurswork_beautysalon_web.repository.FreeRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/freeRecords")
public class FreeRecordsApiAdminController {
    @Autowired
    private FreeRecordsRepository freeRecordsRepository;

    @PutMapping()
    public @ResponseBody
    Object addTicket(HttpServletResponse response, @RequestBody FreeRecords freeRecords) {
        var newFreeRecord = new FreeRecords();
        newFreeRecord.setEmployee(freeRecords.getEmployee());
        newFreeRecord.setDate(freeRecords.getDate());
        var k = freeRecordsRepository.save(newFreeRecord);
        response.setStatus(200);
        return new ErrorInfo(200, "Свободное окошко успешно добавлено с id " + k.getId() + " .");
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    Object removeTicket(HttpServletResponse response, @PathVariable Long id) {
        var freeRecord = freeRecordsRepository.findById(id);
        if (freeRecord.isPresent()) {
            freeRecordsRepository.delete(freeRecord.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Свободное окошко успешно удалено.");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Свободного окошка с введенными данными нет в базе. " +
                    "Измените данные поиска.");
        }
    }

    @GetMapping()
    public @ResponseBody
    List<FreeRecords> getTickets() {
        return freeRecordsRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody
    Object getTicket(HttpServletResponse response, @PathVariable Long id) {
        var freeRecord = freeRecordsRepository.findById(id);
        if (freeRecord.isPresent()) {
            return freeRecord.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Свободного окошка с введенными данными нет в базе. " +
                    "Измените данные поиска.");
        }
    }

    @PostMapping("{id}")
    public Object saveTicket(HttpServletResponse response, @RequestBody @Valid FreeRecords freeRecords,
                             BindingResult bindingResult) {
        if (freeRecords == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос.");
        } else {
            System.out.println(freeRecords.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = freeRecordsRepository.findById(freeRecords.getId()).get();
            correct.setEmployee(freeRecords.getEmployee());
            correct.setDate(freeRecords.getDate());
            freeRecordsRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Свободное окошко успешно обновлено.");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }
}
