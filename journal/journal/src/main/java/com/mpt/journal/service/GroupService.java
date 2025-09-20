package com.mpt.journal.service;

import com.mpt.journal.model.GroupModel;

import java.util.List;

public interface GroupService {
    List<GroupModel> findAllGroups();
    List<GroupModel> findActiveGroups();
    GroupModel findGroupById(int id);
    GroupModel addGroup(GroupModel group);
    GroupModel updateGroup(GroupModel group);
    void deleteGroup(int id); // физическое удаление
    void softDeleteGroup(int id); // логическое удаление
    void deleteGroups(List<Integer> ids); // множественное физическое удаление
    void softDeleteGroups(List<Integer> ids); // множественное логическое удаление
    void restoreGroup(int id); // восстановление удаленной записи
    void restoreGroups(List<Integer> ids); // множественное восстановление
    List<GroupModel> searchGroups(String searchTerm);
    List<GroupModel> filterGroups(String groupName, String specialization, Integer yearOfStudy);
}
