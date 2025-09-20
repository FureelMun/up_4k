package com.mpt.journal.controller;

import com.mpt.journal.model.CourseModel;
import com.mpt.journal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.findAllCourses());
        return "courseList";
    }

    @PostMapping("/add")
    public String addCourse(@RequestParam String courseName,
                           @RequestParam String description,
                           @RequestParam String instructor,
                           @RequestParam int credits) {
        CourseModel newCourse = new CourseModel(0, courseName, description, instructor, credits);
        courseService.addCourse(newCourse);
        return "redirect:/courses";
    }

    @PostMapping("/update")
    public String updateCourse(@RequestParam int id,
                              @RequestParam String courseName,
                              @RequestParam String description,
                              @RequestParam String instructor,
                              @RequestParam int credits) {
        CourseModel updatedCourse = new CourseModel(id, courseName, description, instructor, credits);
        courseService.updateCourse(updatedCourse);
        return "redirect:/courses";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam int id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("/soft-delete")
    public String softDeleteCourse(@RequestParam int id) {
        courseService.softDeleteCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("/bulk-delete")
    public String bulkDeleteCourses(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        courseService.deleteCourses(idList);
        return "redirect:/courses";
    }

    @PostMapping("/bulk-soft-delete")
    public String bulkSoftDeleteCourses(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        courseService.softDeleteCourses(idList);
        return "redirect:/courses";
    }

    @PostMapping("/restore")
    public String restoreCourse(@RequestParam int id) {
        courseService.restoreCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("/bulk-restore")
    public String bulkRestoreCourses(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        courseService.restoreCourses(idList);
        return "redirect:/courses";
    }

    @GetMapping("/search")
    public String searchCourses(@RequestParam String searchTerm, Model model) {
        model.addAttribute("courses", courseService.searchCourses(searchTerm));
        model.addAttribute("searchTerm", searchTerm);
        return "courseList";
    }

    @GetMapping("/filter")
    public String filterCourses(@RequestParam(required = false) String courseName,
                               @RequestParam(required = false) String instructor,
                               @RequestParam(required = false) Integer minCredits,
                               Model model) {
        model.addAttribute("courses", courseService.filterCourses(courseName, instructor, minCredits));
        model.addAttribute("courseName", courseName);
        model.addAttribute("instructor", instructor);
        model.addAttribute("minCredits", minCredits);
        return "courseList";
    }
}
