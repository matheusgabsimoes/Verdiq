package com.verdiq.controller;

import com.verdiq.controller.request.UserRequest;
import com.verdiq.controller.response.UserResponse;
import com.verdiq.entity.User;
import com.verdiq.mapper.UserMapper;
import com.verdiq.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verdiq/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        User savedUser = userService.save(UserMapper
                .toUser(request)
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper
                        .toUserResponse(savedUser)
                );

    }
}
