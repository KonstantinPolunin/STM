package com.stm.tickets.service;

import com.stm.tickets.models.User;
import com.stm.tickets.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void registerUser(User user) {
        try {
            userRepository.registerUser(user);
        }
        catch (SQLException e) {
            throw new RuntimeException("Error registering user", e);
        }
    }

}
