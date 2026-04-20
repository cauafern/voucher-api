package com.caua.voucher_api.controller;

import com.caua.voucher_api.dto.UserRequestDTO;
import com.caua.voucher_api.model.User;
import com.caua.voucher_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user by providing name, email, and password."
    )
    @ApiResponse(responseCode = "201", description = "User successfully created",
            content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "400", description = "Invalid user data")


    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO dto) {
        try {
            return ResponseEntity.status(201).body(userService.registerUser(dto.name(), dto.email(), dto.password()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
