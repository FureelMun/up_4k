package com.mpt.journal.service;


import com.mpt.journal.entity.StudentEntity;
import com.mpt.journal.model.StudentModel;

import java.util.List;

public interface StudentService {
    public List<StudentModel> findAllStudent();
    public List<StudentModel> findActiveStudents();
    public StudentModel findStudentById(int id);
    public StudentModel addStudent(StudentModel student);
    public StudentModel updateStudent(StudentModel student);
    public void deleteStudent(int id); // физическое удаление
    public void softDeleteStudent(int id); // логическое удаление
    public void deleteStudents(List<Integer> ids); // множественное физическое удаление
    public void softDeleteStudents(List<Integer> ids); // множественное логическое удаление
    public void restoreStudent(int id); // восстановление удаленной записи
    public void restoreStudents(List<Integer> ids); // множественное восстановление
    public List<StudentModel> searchStudents(String searchTerm);
    public List<StudentModel> filterStudents(String name, String lastName, Integer yearsOfStudy);
}
