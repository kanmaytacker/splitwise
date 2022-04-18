package com.scaler.splitwise.services;

import com.scaler.splitwise.dtos.CreateGroupDTO;
import com.scaler.splitwise.dtos.GroupDTO;
import com.scaler.splitwise.dtos.SettleResponseDTO;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.models.Transaction;
import com.scaler.splitwise.repositories.GroupRepository;
import com.scaler.splitwise.services.settlement.GreedySettlementStrategy;
import com.scaler.splitwise.services.settlement.SettlementStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final SettlementStrategy settlementStrategy = new GreedySettlementStrategy();
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    public GroupDTO createGroup(CreateGroupDTO createGroupRequest) {
        Group group = groupRepository.save(Group.from(createGroupRequest));
        return GroupDTO.from(group);
    }

    public Optional<Group> getGroup(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public Optional<SettleResponseDTO> settleExpenses(Long groupId) {
        Optional<Group> group = getGroup(groupId);
        if (group.isEmpty()) {
            return Optional.empty();
        }

        List<Expense> groupExpenses = group.get().getExpenses();
        List<Transaction> settlementTransactions = settlementStrategy.settleExpenses(groupExpenses);
        return Optional.of(SettleResponseDTO.builder().transactions(settlementTransactions).build());
    }
}
