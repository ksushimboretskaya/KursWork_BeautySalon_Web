package com.spring.kurswork_beautysalon_web.controller;

import com.spring.kurswork_beautysalon_web.entity.Employee;
import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.repository.EmployeeRepository;
import com.spring.kurswork_beautysalon_web.repository.ServicesRepository;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            var fakeUser = new User();
            fakeUser.setId(-1L);
            fakeUser.setLogin("null");
            model.addAttribute("user", fakeUser);
        }
        model.addAttribute("services", servicesRepository.findAll());
        model.addAttribute("employee", employeeRepository.findAll());
        model.addAttribute("dateNow", LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "hello";
    }

    @RequestMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        File file = new File ("employee.xls");
        List<Employee> list = employeeRepository.findAll(); // данные mysql
        HSSFWorkbook workbook = new HSSFWorkbook(); // Создать книгу
        HSSFSheet sheet = workbook.createSheet("Employee"); // Создаем рабочий лист
        int rowNum = 0;
        String[] headers = {"номер", "ФИО", "Услуга"};
        HSSFRow row = sheet.createRow(rowNum); // Создаем первую строку
        // Строка заголовка
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i); // Создать столбец (ячейку)
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text); // Устанавливаем значение столбца
            sheet.setColumnWidth(i, 256 * 13); // Устанавливаем ширину столбца
        }
        for (Employee employee : list) {
            rowNum++;
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(employee.getId());
            row1.createCell(1).setCellValue(employee.getFullName());
            row1.createCell(2).setCellValue(employee.getServices().getServicesName());
        }
        workbook.write(new FileOutputStream(file));
    }
}