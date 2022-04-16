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

    public static User from(CreateUserDTO userRequest, String encodedPassword) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setHashedPassword(encodedPassword);
        return user;
    }
}
