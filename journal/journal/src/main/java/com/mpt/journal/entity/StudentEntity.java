package com.mpt.journal.entity;

import com.mpt.journal.model.StudentModel;

public class StudentEntity extends StudentModel {

    public StudentEntity(int id, String name, String lastName, String firstName, String middleName, int yearsOfStudy) {
        super(id, name, lastName, firstName, middleName, yearsOfStudy);
    }
}