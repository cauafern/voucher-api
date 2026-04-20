package com.caua.voucher_api.service;

import com.caua.voucher_api.model.Product;
import com.caua.voucher_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product registerProduct(Product product) {
        return productRepository.save(product);
    }
}
