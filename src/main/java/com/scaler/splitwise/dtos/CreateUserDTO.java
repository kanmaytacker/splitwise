package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.User;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateUserDTO {
    private String name;
    private String phoneNumber;
    private String password;

}
