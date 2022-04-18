package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.CreateGroupDTO;
import com.scaler.splitwise.dtos.GroupDTO;
import com.scaler.splitwise.dtos.ResponseDTO;
import com.scaler.splitwise.dtos.SettleResponseDTO;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.services.GroupService;
import com.scaler.splitwise.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/group")
    public ResponseDTO<GroupDTO> createGroup(@RequestBody CreateGroupDTO createGroupRequest) {
        GroupDTO group = groupService.createGroup(createGroupRequest);
        return ResponseUtils.toResponse(Optional.of(group));
    }

    @GetMapping("/group/{groupId}")
    public ResponseDTO<GroupDTO> getGroup(@PathVariable Long groupId) {
        Optional<Group> group = groupService.getGroup(groupId);
        return ResponseUtils.toResponse(group.map(GroupDTO::from));
    }

    @PostMapping("/group/{groupId}/settle")
    public ResponseDTO<SettleResponseDTO> settleGroupExpenses(@PathVariable Long groupId) {
        Optional<SettleResponseDTO> transactions = groupService.settleExpenses(groupId);
        return ResponseUtils.toResponse(transactions);
    }
}
