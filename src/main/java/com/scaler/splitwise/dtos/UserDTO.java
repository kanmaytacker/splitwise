package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long userId;
    private String name;
    private String phoneNumber;

    public static UserDTO from(User user) {
        return UserDTO.builder().userId(user.getId()).name(user.getName()).phoneNumber(user.getPhoneNumber()).build();
    }
}
