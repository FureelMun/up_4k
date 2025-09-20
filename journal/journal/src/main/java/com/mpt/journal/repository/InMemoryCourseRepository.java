package com.mpt.journal.repository;

import com.mpt.journal.model.CourseModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryCourseRepository {
    private List<CourseModel> courses = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public CourseModel addCourse(CourseModel course) {
        course.setId(idCounter.getAndIncrement());
        courses.add(course);
        return course;
    }

    public CourseModel updateCourse(CourseModel course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == course.getId()) {
                courses.set(i, course);
                return course;
            }
        }
        return null;
    }

    // Физическое удаление
    public void deleteCourse(int id) {
        courses.removeIf(course -> course.getId() == id);
    }

    // Логическое удаление
    public void softDeleteCourse(int id) {
        courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .ifPresent(course -> course.setDeleted(true));
    }

    // Множественное логическое удаление
    public void softDeleteCourses(List<Integer> ids) {
        courses.stream()
                .filter(course -> ids.contains(course.getId()))
                .forEach(course -> course.setDeleted(true));
    }

    // Множественное физическое удаление
    public void deleteCourses(List<Integer> ids) {
        courses.removeIf(course -> ids.contains(course.getId()));
    }

    // Восстановление удаленной записи
    public void restoreCourse(int id) {
        courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .ifPresent(course -> course.setDeleted(false));
    }

    // Множественное восстановление
    public void restoreCourses(List<Integer> ids) {
        courses.stream()
                .filter(course -> ids.contains(course.getId()))
                .forEach(course -> course.setDeleted(false));
    }

    public List<CourseModel> findAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<CourseModel> findActiveCourses() {
        return courses.stream()
                .filter(course -> !course.isDeleted())
                .collect(Collectors.toList());
    }

    public CourseModel findCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Поиск по параметрам
    public List<CourseModel> searchCourses(String searchTerm) {
        return courses.stream()
                .filter(course -> !course.isDeleted())
                .filter(course -> 
                    course.getCourseName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    course.getDescription().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    course.getInstructor().toLowerCase().contains(searchTerm.toLowerCase())
                )
                .collect(Collectors.toList());
    }

    // Фильтрация по критериям
    public List<CourseModel> filterCourses(String courseName, String instructor, Integer minCredits) {
        return courses.stream()
                .filter(course -> !course.isDeleted())
                .filter(course -> courseName == null || course.getCourseName().toLowerCase().contains(courseName.toLowerCase()))
                .filter(course -> instructor == null || course.getInstructor().toLowerCase().contains(instructor.toLowerCase()))
                .filter(course -> minCredits == null || course.getCredits() >= minCredits)
                .collect(Collectors.toList());
    }
}
