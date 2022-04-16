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
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody CreateUserDTO userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/user/{userId}")
    public ResponseDTO<UserDTO> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUser(userId);
        Optional<UserDTO> userDTO = user.map(UserDTO::from);

        return userDTO.map(ResponseDTO::success).orElse(ResponseDTO.notFound());
    }

    @PutMapping("/user/{userId}")
    public ResponseDTO<UserDTO> updateUser(@PathVariable Long userId, @RequestBody CreateUserDTO updateRequest) {
        UserDTO user = userService.updateUser(userId, updateRequest);
        if (user == null) {
            return ResponseDTO.notFound();
        }

        return ResponseDTO.success(user);
    }

    // Create an expense
    // HTTP VERB - POST Endpoint => /api/v1/expense = user or group
    // HTTP VERB - POST Endpoint => /api/v1/user/:userId/expense = only a user expense
    // Input user expense => Convert user Id in owed by and paid By to user

}

// Create user = HTTP VERB => POST ENDPOINT => /api/v1/user
// Fetch user = HTTP VERB => GET ENDPOINT => /api/v1/user/:userID
// Update user = HTTP VERB => PUT ENDPOINT => /api/v1/user/:userID
// For each HTTP response, there is a status code
// If request is successful, 200
// If entity is not found, 404
// If request is not valid or invalid input, 400
// 2xx - OK
// 4xx - User error
// 5xx - Server error
