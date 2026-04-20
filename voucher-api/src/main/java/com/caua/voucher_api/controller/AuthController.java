package com.caua.voucher_api.controller;

import com.caua.voucher_api.dto.LoginRequestDTO;
import com.caua.voucher_api.repository.UserRepository;
import com.caua.voucher_api.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    @Operation(
            summary = "User login",
            description = "Authenticates a user with email and password. Returns a JWT token if credentials are valid."
    )
    @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned",
            content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Invalid credentials or user not found")


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
        var user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (passwordEncoder.matches(dto.password(), user.getPassword())) {
            return ResponseEntity.ok(jwtService.generateToken(user));
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }
}