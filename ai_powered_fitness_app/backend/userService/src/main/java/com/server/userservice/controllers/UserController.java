package com.server.userservice.controllers;

import com.server.userservice.dtos.response.UserResponse;
import com.server.userservice.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<UserResponse> getUserDetails(
            @RequestHeader("X-User-Id") String userId
    ) {
        return ResponseEntity.ok(userService.findById(userId));
    }
}
