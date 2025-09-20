package com.mpt.journal.model;

public class CourseModel {
    private int id;
    private String courseName;
    private String description;
    private String instructor;
    private int credits;
    private boolean isDeleted; // для логического удаления

    public CourseModel(int id, String courseName, String description, String instructor, int credits) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.instructor = instructor;
        this.credits = credits;
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
