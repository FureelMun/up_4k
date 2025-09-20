package com.mpt.journal.service;

import com.mpt.journal.entity.StudentEntity;
import com.mpt.journal.model.StudentModel;
import com.mpt.journal.repository.InMemoryStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


//Сервисный слой отвечает за бизнес-логику приложения. Он использует репозиторий для выполнения операций с данными и может включать дополнительные проверки или преобразования данных
//так же мы тут можем настроить инкапсуляцию
//А если простыми словами тут происходит разделенние запросов от контроллера к сервису
@Service
public class InMemoryStudentServiceImpl implements StudentService {

    private final InMemoryStudentRepository studentRepository;

    public InMemoryStudentServiceImpl(InMemoryStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentModel> findAllStudent() {
        return studentRepository.findAllStudents();
    }

    @Override
    public StudentModel findStudentById(int id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public StudentModel addStudent(StudentModel student) {
        return studentRepository.addStudent(student);
    }

    @Override
    public StudentModel updateStudent(StudentModel student) {
        return studentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }

    @Override
    public List<StudentModel> findActiveStudents() {
        return studentRepository.findActiveStudents();
    }

    @Override
    public void softDeleteStudent(int id) {
        studentRepository.softDeleteStudent(id);
    }

    @Override
    public void deleteStudents(List<Integer> ids) {
        studentRepository.deleteStudents(ids);
    }

    @Override
    public void softDeleteStudents(List<Integer> ids) {
        studentRepository.softDeleteStudents(ids);
    }

    @Override
    public void restoreStudent(int id) {
        studentRepository.restoreStudent(id);
    }

    @Override
    public void restoreStudents(List<Integer> ids) {
        studentRepository.restoreStudents(ids);
    }

    @Override
    public List<StudentModel> searchStudents(String searchTerm) {
        return studentRepository.searchStudents(searchTerm);
    }

    @Override
    public List<StudentModel> filterStudents(String name, String lastName, Integer yearsOfStudy) {
        return studentRepository.filterStudents(name, lastName, yearsOfStudy);
    }
}
