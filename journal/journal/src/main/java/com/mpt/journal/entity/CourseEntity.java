package com.mpt.journal.entity;

import com.mpt.journal.model.CourseModel;

public class CourseEntity extends CourseModel {

    public CourseEntity(int id, String courseName, String description, String instructor, int credits) {
        super(id, courseName, description, instructor, credits);
    }
}
