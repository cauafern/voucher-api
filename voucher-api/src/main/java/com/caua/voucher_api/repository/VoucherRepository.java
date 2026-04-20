package com.caua.voucher_api.repository;

import com.caua.voucher_api.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}
