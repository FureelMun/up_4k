package com.mpt.journal.service;

import com.mpt.journal.model.GroupModel;
import com.mpt.journal.repository.InMemoryGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryGroupServiceImpl implements GroupService {

    private final InMemoryGroupRepository groupRepository;

    public InMemoryGroupServiceImpl(InMemoryGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<GroupModel> findAllGroups() {
        return groupRepository.findAllGroups();
    }

    @Override
    public List<GroupModel> findActiveGroups() {
        return groupRepository.findActiveGroups();
    }

    @Override
    public GroupModel findGroupById(int id) {
        return groupRepository.findGroupById(id);
    }

    @Override
    public GroupModel addGroup(GroupModel group) {
        return groupRepository.addGroup(group);
    }

    @Override
    public GroupModel updateGroup(GroupModel group) {
        return groupRepository.updateGroup(group);
    }

    @Override
    public void deleteGroup(int id) {
        groupRepository.deleteGroup(id);
    }

    @Override
    public void softDeleteGroup(int id) {
        groupRepository.softDeleteGroup(id);
    }

    @Override
    public void deleteGroups(List<Integer> ids) {
        groupRepository.deleteGroups(ids);
    }

    @Override
    public void softDeleteGroups(List<Integer> ids) {
        groupRepository.softDeleteGroups(ids);
    }

    @Override
    public void restoreGroup(int id) {
        groupRepository.restoreGroup(id);
    }

    @Override
    public void restoreGroups(List<Integer> ids) {
        groupRepository.restoreGroups(ids);
    }

    @Override
    public List<GroupModel> searchGroups(String searchTerm) {
        return groupRepository.searchGroups(searchTerm);
    }

    @Override
    public List<GroupModel> filterGroups(String groupName, String specialization, Integer yearOfStudy) {
        return groupRepository.filterGroups(groupName, specialization, yearOfStudy);
    }
}
