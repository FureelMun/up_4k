package com.mpt.journal.model;

public class GroupModel {
    private int id;
    private String groupName;
    private String specialization;
    private int yearOfStudy;
    private boolean isDeleted; // для логического удаления

    public GroupModel(int id, String groupName, String specialization, int yearOfStudy) {
        this.id = id;
        this.groupName = groupName;
        this.specialization = specialization;
        this.yearOfStudy = yearOfStudy;
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
