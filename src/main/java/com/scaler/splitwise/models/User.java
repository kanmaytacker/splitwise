package com.scaler.splitwise.models;

import com.scaler.splitwise.dtos.CreateUserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "USERS")
public class User extends BaseModel {
    private String name;
    private String phoneNumber;
    private String hashedPassword;


    public static User from(CreateUserDTO userDTO, String hashedPassword) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setHashedPassword(hashedPassword);
        return user;
    }
}
