package com.caua.voucher_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record VoucherRequestDTO(
        @NotBlank String code,
        @NotBlank Double discount,
        @NotBlank LocalDateTime expirationDate,
        @NotBlank Long userId,
        @NotBlank Long productId,
        @NotBlank Integer usageLimit,
        @NotBlank Boolean flashPromotion ){
}
