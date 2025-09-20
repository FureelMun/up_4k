package com.mpt.journal.controller;

import com.mpt.journal.model.StudentModel;
import com.mpt.journal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//Основная бизнес-логика нашего проекта
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.findAllStudent()); // выгрузка всех студентов на экран (включая удаленных)
        return "studentList";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam String name,
                             @RequestParam String lastName,
                             @RequestParam String firstName,
                             @RequestParam String middleName,
                             @RequestParam int yearsOfStudy) {
        StudentModel newStudent = new StudentModel(0, name, lastName, firstName, middleName, yearsOfStudy); // тут мы получаем данные с главных полей, id задается автоматически из нашего репозитория
        studentService.addStudent(newStudent); // добавление студента в оперативную память(после перезапуска проекта, все данные стираются)
        return "redirect:/students"; // Здесь мы с вами используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping("/students/update")
    public String updateStudent(@RequestParam int id,
                                @RequestParam String name,
                                @RequestParam String lastName,
                                @RequestParam String firstName,
                                @RequestParam String middleName,
                                @RequestParam int yearsOfStudy) {
        StudentModel updatedStudent = new StudentModel(id, name, lastName, firstName, middleName, yearsOfStudy); // Получаем новые данные из полей для обновления
        studentService.updateStudent(updatedStudent); // Ссылаемся на наш сервис для обновления по id
        return "redirect:/students"; // Здесь мы с вами используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteStudent(id); // Ссылаемся на наш сервис для удаления по id
        return "redirect:/students"; // Здесь мы с вами используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping("/students/soft-delete")
    public String softDeleteStudent(@RequestParam int id) {
        studentService.softDeleteStudent(id);
        return "redirect:/students";
    }

    @PostMapping("/students/bulk-delete")
    public String bulkDeleteStudents(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        studentService.deleteStudents(idList);
        return "redirect:/students";
    }

    @PostMapping("/students/bulk-soft-delete")
    public String bulkSoftDeleteStudents(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        studentService.softDeleteStudents(idList);
        return "redirect:/students";
    }

    @PostMapping("/students/restore")
    public String restoreStudent(@RequestParam int id) {
        studentService.restoreStudent(id);
        return "redirect:/students";
    }

    @PostMapping("/students/bulk-restore")
    public String bulkRestoreStudents(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        studentService.restoreStudents(idList);
        return "redirect:/students";
    }

    @GetMapping("/students/search")
    public String searchStudents(@RequestParam String searchTerm, Model model) {
        model.addAttribute("students", studentService.searchStudents(searchTerm));
        model.addAttribute("searchTerm", searchTerm);
        return "studentList";
    }

    @GetMapping("/students/filter")
    public String filterStudents(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String lastName,
                                @RequestParam(required = false) Integer yearsOfStudy,
                                Model model) {
        model.addAttribute("students", studentService.filterStudents(name, lastName, yearsOfStudy));
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("yearsOfStudy", yearsOfStudy);
        return "studentList";
    }
}
