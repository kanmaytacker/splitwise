package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.CreateUserDTO;
import com.scaler.splitwise.dtos.ResponseDTO;
import com.scaler.splitwise.dtos.UserDTO;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{userId}")
    @ResponseBody
    public ResponseDTO<UserDTO> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUser(userId);
        return user.map(UserDTO::from).map(ResponseDTO::success).orElse(ResponseDTO.notFound());
    }

    @PostMapping(value = "/user")
    public UserDTO createUser(@RequestBody CreateUserDTO userRequest) {
        return userService.createUser(userRequest);
    }
}
