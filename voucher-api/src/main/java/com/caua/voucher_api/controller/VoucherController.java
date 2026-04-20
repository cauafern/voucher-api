package com.caua.voucher_api.controller;

import com.caua.voucher_api.dto.VoucherRequestDTO;
import com.caua.voucher_api.model.Voucher;
import com.caua.voucher_api.service.VoucherService;
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

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    @Operation(
            summary = "Generate voucher",
            description = "Generates a voucher for a specific user, all users, or a flash promotion depending on the request parameters."
    )
    @ApiResponse(responseCode = "200", description = "Voucher(s) successfully generated",
            content = @Content(schema = @Schema(implementation = Voucher.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @ApiResponse(responseCode = "403", description = "Access denied")


    @PostMapping("/generate")
    public ResponseEntity<?> generateVouchers(@RequestBody VoucherRequestDTO dto) {
        if (dto.userId() != null) {
            var voucher = voucherService.generateForUser(dto.userId(), dto.productId(), dto.discount(), dto.expirationDate());
            return ResponseEntity.ok(voucher);
        }
        if (dto.flashPromotion()) {
            var voucher = voucherService.generateFlashPromotionVoucher(dto.productId(), dto.discount(), dto.expirationDate(), dto.usageLimit());

            return ResponseEntity.ok(voucher);
        }
        var vouchers = voucherService.generateForAllUsers(dto.productId(), dto.discount(), dto.expirationDate());
        return ResponseEntity.ok(vouchers);
    }

    @Operation(
            summary = "Generate vouchers for all users",
            description = "Creates vouchers for all registered users in the system."
    )
    @ApiResponse(responseCode = "200", description = "Vouchers successfully generated",
            content = @Content(schema = @Schema(implementation = Voucher.class)))

    @PostMapping("/generate/all")
    public ResponseEntity<List<Voucher>> generateForAll(@RequestBody VoucherRequestDTO dto) {
        var vouchers = voucherService.generateForAllUsers(dto.productId(), dto.discount(), dto.expirationDate());
        return ResponseEntity.ok(vouchers);
    }

    @Operation(
            summary = "Generate flash promotion voucher",
            description = "Creates a flash promotion voucher with a usage limit."
    )
    @ApiResponse(responseCode = "200", description = "Flash promotion voucher successfully generated",
            content = @Content(schema = @Schema(implementation = Voucher.class)))


    @PostMapping("/generate/flash")
    public ResponseEntity<Voucher> generateFlash(@RequestBody VoucherRequestDTO dto) {
        var voucher = voucherService.generateFlashPromotionVoucher(dto.productId(), dto.discount(), dto.expirationDate(), dto.usageLimit());
        return ResponseEntity.ok(voucher);
    }

}
