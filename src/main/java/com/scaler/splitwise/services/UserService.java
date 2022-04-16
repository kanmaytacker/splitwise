package com.scaler.splitwise.services;

import com.scaler.splitwise.dtos.CreateUserDTO;
import com.scaler.splitwise.dtos.UserDTO;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(CreateUserDTO userRequest) {

        // Encode plaintext password
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());

        // Convert DTO to user
        User user = User.from(userRequest, hashedPassword);

        // Persist the user
        User persistedUser = userRepository.save(user);
        return UserDTO.from(persistedUser);
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public UserDTO updateUser(Long userId, CreateUserDTO updateRequest) {
        // Check if user exists
        if (!userRepository.existsById(userId)) {
            return null;
        }

        String hashedPassword = passwordEncoder.encode(updateRequest.getPassword());
        User user = User.from(updateRequest, hashedPassword);
        user.setId(userId);

        User persistedUser = userRepository.save(user);
        return UserDTO.from(persistedUser);
    }
}

// MapStruct
// Optional.isPresent
