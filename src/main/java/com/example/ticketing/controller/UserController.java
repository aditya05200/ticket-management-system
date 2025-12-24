package com.example.ticketing.controller;

import com.example.ticketing.dto.UserRequestDTO;
import com.example.ticketing.dto.UserResponseDTO;
import com.example.ticketing.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto) {
        return userService.register(dto);
    }
}
