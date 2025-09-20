package com.mpt.journal.controller;

import com.mpt.journal.model.GroupModel;
import com.mpt.journal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public String getAllGroups(Model model) {
        model.addAttribute("groups", groupService.findAllGroups());
        return "groupList";
    }

    @PostMapping("/add")
    public String addGroup(@RequestParam String groupName,
                          @RequestParam String specialization,
                          @RequestParam int yearOfStudy) {
        GroupModel newGroup = new GroupModel(0, groupName, specialization, yearOfStudy);
        groupService.addGroup(newGroup);
        return "redirect:/groups";
    }

    @PostMapping("/update")
    public String updateGroup(@RequestParam int id,
                             @RequestParam String groupName,
                             @RequestParam String specialization,
                             @RequestParam int yearOfStudy) {
        GroupModel updatedGroup = new GroupModel(id, groupName, specialization, yearOfStudy);
        groupService.updateGroup(updatedGroup);
        return "redirect:/groups";
    }

    @PostMapping("/delete")
    public String deleteGroup(@RequestParam int id) {
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }

    @PostMapping("/soft-delete")
    public String softDeleteGroup(@RequestParam int id) {
        groupService.softDeleteGroup(id);
        return "redirect:/groups";
    }

    @PostMapping("/bulk-delete")
    public String bulkDeleteGroups(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        groupService.deleteGroups(idList);
        return "redirect:/groups";
    }

    @PostMapping("/bulk-soft-delete")
    public String bulkSoftDeleteGroups(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        groupService.softDeleteGroups(idList);
        return "redirect:/groups";
    }

    @PostMapping("/restore")
    public String restoreGroup(@RequestParam int id) {
        groupService.restoreGroup(id);
        return "redirect:/groups";
    }

    @PostMapping("/bulk-restore")
    public String bulkRestoreGroups(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        groupService.restoreGroups(idList);
        return "redirect:/groups";
    }

    @GetMapping("/search")
    public String searchGroups(@RequestParam String searchTerm, Model model) {
        model.addAttribute("groups", groupService.searchGroups(searchTerm));
        model.addAttribute("searchTerm", searchTerm);
        return "groupList";
    }

    @GetMapping("/filter")
    public String filterGroups(@RequestParam(required = false) String groupName,
                              @RequestParam(required = false) String specialization,
                              @RequestParam(required = false) Integer yearOfStudy,
                              Model model) {
        model.addAttribute("groups", groupService.filterGroups(groupName, specialization, yearOfStudy));
        model.addAttribute("groupName", groupName);
        model.addAttribute("specialization", specialization);
        model.addAttribute("yearOfStudy", yearOfStudy);
        return "groupList";
    }
}
