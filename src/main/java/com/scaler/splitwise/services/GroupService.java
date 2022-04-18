package com.scaler.splitwise.services;

import com.scaler.splitwise.dtos.CreateGroupDTO;
import com.scaler.splitwise.dtos.GroupDTO;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupDTO createGroup(CreateGroupDTO createGroupRequest) {
        Group group = groupRepository.save(Group.from(createGroupRequest));
        return GroupDTO.from(group);
    }

    public Optional<Group> getGroup(Long groupId) {
        return groupRepository.findById(groupId);
    }
}
