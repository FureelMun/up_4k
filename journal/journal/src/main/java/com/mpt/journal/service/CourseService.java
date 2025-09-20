package com.mpt.journal.service;

import com.mpt.journal.model.CourseModel;

import java.util.List;

public interface CourseService {
    List<CourseModel> findAllCourses();
    List<CourseModel> findActiveCourses();
    CourseModel findCourseById(int id);
    CourseModel addCourse(CourseModel course);
    CourseModel updateCourse(CourseModel course);
    void deleteCourse(int id); // физическое удаление
    void softDeleteCourse(int id); // логическое удаление
    void deleteCourses(List<Integer> ids); // множественное физическое удаление
    void softDeleteCourses(List<Integer> ids); // множественное логическое удаление
    void restoreCourse(int id); // восстановление удаленной записи
    void restoreCourses(List<Integer> ids); // множественное восстановление
    List<CourseModel> searchCourses(String searchTerm);
    List<CourseModel> filterCourses(String courseName, String instructor, Integer minCredits);
}
