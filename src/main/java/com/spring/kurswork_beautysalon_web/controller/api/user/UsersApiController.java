package com.spring.kurswork_beautysalon_web.controller.api.user;

import com.spring.kurswork_beautysalon_web.controller.ControllerUtils;
import com.spring.kurswork_beautysalon_web.entity.BookedRecords;
import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.entity.api.ErrorInfo;
import com.spring.kurswork_beautysalon_web.repository.BookedRecordsRepository;
import com.spring.kurswork_beautysalon_web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersApiController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BookedRecordsRepository bookedRecordsRepository;

    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public @ResponseBody
    List<BookedRecords> getBookedTickets(@AuthenticationPrincipal User user) {
        var fullUser = userRepository.getOne(user.getId());
        var records = new ArrayList<BookedRecords>();
        for (var rec : fullUser.getBookedRecords()) {
            rec.setUser(user);
            records.add(rec);
        }
        return records;
    }

    @GetMapping("/records/sell")
    public @ResponseBody
    Object sellTicker(@AuthenticationPrincipal User user, @RequestParam long id) {
        bookedRecordsRepository.delete(bookedRecordsRepository.getOne(id));
        return new ErrorInfo(200, "Успешно!");
    }

    @PostMapping("")
    public Object saveUser(@AuthenticationPrincipal User current_user, HttpServletResponse response,
                           @RequestBody @Valid User user, BindingResult bindingResult) {
        if (user == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформирован запрос");
        } else {
            System.out.println(user.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = userRepository.findById(current_user.getId()).get();
            correct.setLogin(user.getLogin());
            correct.setEmail(user.getEmail());
            correct.setFullName(user.getFullName());
            if (user.getPassword() != null && !user.getPassword().equals("Пароль")) {
                correct.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Данные успешно обновлены");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }
}