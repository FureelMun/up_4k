package com.mpt.journal.service;

import com.mpt.journal.model.CourseModel;
import com.mpt.journal.repository.InMemoryCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryCourseServiceImpl implements CourseService {

    private final InMemoryCourseRepository courseRepository;

    public InMemoryCourseServiceImpl(InMemoryCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseModel> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    @Override
    public List<CourseModel> findActiveCourses() {
        return courseRepository.findActiveCourses();
    }

    @Override
    public CourseModel findCourseById(int id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public CourseModel addCourse(CourseModel course) {
        return courseRepository.addCourse(course);
    }

    @Override
    public CourseModel updateCourse(CourseModel course) {
        return courseRepository.updateCourse(course);
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteCourse(id);
    }

    @Override
    public void softDeleteCourse(int id) {
        courseRepository.softDeleteCourse(id);
    }

    @Override
    public void deleteCourses(List<Integer> ids) {
        courseRepository.deleteCourses(ids);
    }

    @Override
    public void softDeleteCourses(List<Integer> ids) {
        courseRepository.softDeleteCourses(ids);
    }

    @Override
    public void restoreCourse(int id) {
        courseRepository.restoreCourse(id);
    }

    @Override
    public void restoreCourses(List<Integer> ids) {
        courseRepository.restoreCourses(ids);
    }

    @Override
    public List<CourseModel> searchCourses(String searchTerm) {
        return courseRepository.searchCourses(searchTerm);
    }

    @Override
    public List<CourseModel> filterCourses(String courseName, String instructor, Integer minCredits) {
        return courseRepository.filterCourses(courseName, instructor, minCredits);
    }
}
