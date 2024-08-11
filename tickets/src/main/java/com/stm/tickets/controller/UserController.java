package com.stm.tickets.controller;

import com.stm.tickets.models.User;
import com.stm.tickets.service.UserService;
import com.stm.tickets.validation.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Контроллер пользователей")
@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @ApiOperation(value = "Регистрация пользователя")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userValidator.validateUser(user);
        userService.registerUser(user);
        return ResponseEntity.ok("User register successfully");
    }
}
