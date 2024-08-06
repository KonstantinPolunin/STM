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

            if (user.getLogin() == null ||
                    user.getPassword() == null ||
                    user.getFirstName() == null ||
                    user.getLastName() == null) {
                throw new IllegalAccessException("All fields are required");
            }
            if (userRepository.findUserByLogin(user.getLogin()) != null) {
                throw new IllegalAccessException("Login is already taken");
            }
            userRepository.registerUser(user);
        }
        catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Error registering user", e);
        }
    }

}
