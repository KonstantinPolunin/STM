package com.stm.tickets.controller;

import com.stm.tickets.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthRestController {

    @GetMapping("api/auth")
    public ResponseEntity<User> userAuth(@AuthenticationPrincipal User userAuth) {
        return ResponseEntity.ok(userAuth);
    }
}
