package com.mpt.journal.repository;

import com.mpt.journal.model.GroupModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryGroupRepository {
    private List<GroupModel> groups = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public GroupModel addGroup(GroupModel group) {
        group.setId(idCounter.getAndIncrement());
        groups.add(group);
        return group;
    }

    public GroupModel updateGroup(GroupModel group) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == group.getId()) {
                groups.set(i, group);
                return group;
            }
        }
        return null;
    }

    // Физическое удаление
    public void deleteGroup(int id) {
        groups.removeIf(group -> group.getId() == id);
    }

    // Логическое удаление
    public void softDeleteGroup(int id) {
        groups.stream()
                .filter(group -> group.getId() == id)
                .findFirst()
                .ifPresent(group -> group.setDeleted(true));
    }

    // Множественное логическое удаление
    public void softDeleteGroups(List<Integer> ids) {
        groups.stream()
                .filter(group -> ids.contains(group.getId()))
                .forEach(group -> group.setDeleted(true));
    }

    // Множественное физическое удаление
    public void deleteGroups(List<Integer> ids) {
        groups.removeIf(group -> ids.contains(group.getId()));
    }

    // Восстановление удаленной записи
    public void restoreGroup(int id) {
        groups.stream()
                .filter(group -> group.getId() == id)
                .findFirst()
                .ifPresent(group -> group.setDeleted(false));
    }

    // Множественное восстановление
    public void restoreGroups(List<Integer> ids) {
        groups.stream()
                .filter(group -> ids.contains(group.getId()))
                .forEach(group -> group.setDeleted(false));
    }

    public List<GroupModel> findAllGroups() {
        return new ArrayList<>(groups);
    }

    public List<GroupModel> findActiveGroups() {
        return groups.stream()
                .filter(group -> !group.isDeleted())
                .collect(Collectors.toList());
    }

    public GroupModel findGroupById(int id) {
        return groups.stream()
                .filter(group -> group.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Поиск по параметрам
    public List<GroupModel> searchGroups(String searchTerm) {
        return groups.stream()
                .filter(group -> !group.isDeleted())
                .filter(group -> 
                    group.getGroupName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    group.getSpecialization().toLowerCase().contains(searchTerm.toLowerCase())
                )
                .collect(Collectors.toList());
    }

    // Фильтрация по критериям
    public List<GroupModel> filterGroups(String groupName, String specialization, Integer yearOfStudy) {
        return groups.stream()
                .filter(group -> !group.isDeleted())
                .filter(group -> groupName == null || group.getGroupName().toLowerCase().contains(groupName.toLowerCase()))
                .filter(group -> specialization == null || group.getSpecialization().toLowerCase().contains(specialization.toLowerCase()))
                .filter(group -> yearOfStudy == null || group.getYearOfStudy() == yearOfStudy)
                .collect(Collectors.toList());
    }
}
