package com.example.ticketing.service;

import com.example.ticketing.dto.UserRequestDTO;
import com.example.ticketing.dto.UserResponseDTO;
import com.example.ticketing.entity.User;
import com.example.ticketing.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTER ONLY (NO AUTHENTICATE LOGIC HERE)
    public UserResponseDTO register(UserRequestDTO dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());

        // ✅ ENCODE PASSWORD
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // ✅ STORE ROLE WITHOUT PREFIX
        user.setRole(dto.getRole()); // USER / AGENT

        User saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getUsername(),
                saved.getRole()
        );
    }
}
