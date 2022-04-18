package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.CreateExpenseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateGroupDTO {
    private String name;
    private Long admin;
    private List<Long> members = new ArrayList<>();
    private List<CreateExpenseDTO> expenses = new ArrayList<>();
}
