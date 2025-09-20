package com.mpt.journal.repository;

import com.mpt.journal.model.StudentModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository

//Репозиторий отвечает за хранение и управление данными студентов в памяти. Он предоставляет методы для выполнения операций(обычные CRUD действия с данными)
public class InMemoryStudentRepository {
    private List<StudentModel> students = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1); // Генерация уникального ID

    public StudentModel addStudent(StudentModel student) {
        student.setId(idCounter.getAndIncrement()); // Установка уникального ID
        students.add(student);
        return student;
    }

    public StudentModel updateStudent(StudentModel student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return student;
            }
        }
        return null; // Студент не найден
    }

    // Физическое удаление
    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    // Логическое удаление
    public void softDeleteStudent(int id) {
        students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .ifPresent(student -> student.setDeleted(true));
    }

    // Множественное логическое удаление
    public void softDeleteStudents(List<Integer> ids) {
        students.stream()
                .filter(student -> ids.contains(student.getId()))
                .forEach(student -> student.setDeleted(true));
    }

    // Множественное физическое удаление
    public void deleteStudents(List<Integer> ids) {
        students.removeIf(student -> ids.contains(student.getId()));
    }

    // Восстановление удаленной записи
    public void restoreStudent(int id) {
        students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .ifPresent(student -> student.setDeleted(false));
    }

    // Множественное восстановление
    public void restoreStudents(List<Integer> ids) {
        students.stream()
                .filter(student -> ids.contains(student.getId()))
                .forEach(student -> student.setDeleted(false));
    }

    public List<StudentModel> findAllStudents() {
        return new ArrayList<>(students);
    }

    public List<StudentModel> findActiveStudents() {
        return students.stream()
                .filter(student -> !student.isDeleted())
                .collect(Collectors.toList());
    }

    public StudentModel findStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Поиск по параметрам
    public List<StudentModel> searchStudents(String searchTerm) {
        return students.stream()
                .filter(student -> !student.isDeleted())
                .filter(student -> 
                    student.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    student.getLastName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    student.getFirstName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    student.getMiddleName().toLowerCase().contains(searchTerm.toLowerCase())
                )
                .collect(Collectors.toList());
    }

    // Фильтрация по критериям
    public List<StudentModel> filterStudents(String name, String lastName, Integer yearsOfStudy) {
        return students.stream()
                .filter(student -> !student.isDeleted())
                .filter(student -> name == null || student.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(student -> lastName == null || student.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .filter(student -> yearsOfStudy == null || student.getYearsOfStudy() == yearsOfStudy)
                .collect(Collectors.toList());
    }
}
