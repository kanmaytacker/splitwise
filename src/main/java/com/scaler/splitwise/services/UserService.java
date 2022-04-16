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

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public UserDTO createUser(CreateUserDTO userRequest) {

        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = userRepository.save(User.from(userRequest, encodedPassword));

        return UserDTO.from(user);
    }
}
