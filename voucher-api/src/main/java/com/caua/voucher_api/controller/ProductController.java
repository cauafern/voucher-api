package com.caua.voucher_api.controller;

import com.caua.voucher_api.dto.ProductRequestDTO;
import com.caua.voucher_api.model.Product;
import com.caua.voucher_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/products")

public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Register a new product",
            description = "Creates a new product by providing its name and price."
    )
    @ApiResponse(responseCode = "201", description = "Product successfully created",
            content = @Content(schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Invalid product data")


    @PostMapping()
    public ResponseEntity<Product> registerProduct(@RequestBody ProductRequestDTO dto) {
        try {
            var product = new Product();
            product.setName(dto.name());
            product.setPrice(dto.price());
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.registerProduct(product));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
