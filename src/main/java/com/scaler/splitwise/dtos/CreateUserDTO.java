package com.scaler.splitwise.dtos;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
}
