package com.stm.tickets.controller;


import com.stm.tickets.models.User;
import com.stm.tickets.service.UserService;
import com.stm.tickets.validation.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserValidator userValidator;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setFirstName("User");
        user.setLastName("User");
    }

    @Test
    public void RegisterUserPositiveTest() {

        doNothing().when(userValidator).validateUser(user);
        doNothing().when(userService).registerUser(user);


        ResponseEntity<?> response = userController.registerUser(user);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User register successfully", response.getBody());


        verify(userValidator, times(1)).validateUser(user);
        verify(userService, times(1)).registerUser(user);
    }


}
