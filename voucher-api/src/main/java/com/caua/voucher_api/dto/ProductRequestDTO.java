package com.caua.voucher_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank String name, @NotBlank BigDecimal price) {
}
