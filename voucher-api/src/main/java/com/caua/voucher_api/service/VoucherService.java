package com.caua.voucher_api.service;

import com.caua.voucher_api.model.Product;
import com.caua.voucher_api.model.User;
import com.caua.voucher_api.model.Voucher;
import com.caua.voucher_api.repository.ProductRepository;
import com.caua.voucher_api.repository.UserRepository;
import com.caua.voucher_api.repository.VoucherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Voucher generateForUser(Long userId, Long productId, Double discount, LocalDateTime expirationDate) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Voucher voucher = new Voucher();
        voucher.setCode("VOUCHER-" + System.currentTimeMillis());
        voucher.setDiscount(discount);
        voucher.setExpirationDate(expirationDate);
        voucher.setUsed(false);

        voucher.setUser(user);
        voucher.setProduct(product);

        return voucherRepository.save(voucher);
    }

    public List<Voucher> generateForAllUsers(Long productId, Double discount, LocalDateTime expirationDate) {
        List<User> allUsers = userRepository.findAll();
        List<Voucher> createdVouchers = new ArrayList<>();

        for (User user : allUsers) {
            Voucher voucher = generateForUser(user.getId(), productId, discount, expirationDate);
            createdVouchers.add(voucher);
        }
        return createdVouchers;
    }

    public  Voucher generateFlashPromotionVoucher(Long productId, Double discount, LocalDateTime expirationDate, Integer limit) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Voucher voucher = new Voucher();
        voucher.setCode("FLASH-" + System.currentTimeMillis());
        voucher.setDiscount(discount);
        voucher.setExpirationDate(expirationDate);
        voucher.setUsed(false);

         voucher.setProduct(product);
         voucher.setUsageLimit(limit);

         return voucherRepository.save(voucher);
    }
}
