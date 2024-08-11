package com.stm.tickets.validation;

import com.stm.tickets.models.User;
import com.stm.tickets.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUser(User user) {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("Login cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long");
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new ValidationException("Firstname name cannot be empty");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new ValidationException("Lastname name cannot be empty");
        }
        if (userRepository.findUserByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login already exists");
        }
    }
}
