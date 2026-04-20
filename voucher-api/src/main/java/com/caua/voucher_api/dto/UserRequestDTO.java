package com.caua.voucher_api.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password) {
}
