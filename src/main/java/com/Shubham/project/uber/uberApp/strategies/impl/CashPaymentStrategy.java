package com.Shubham.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.Shubham.project.uber.uberApp.entities.Driver;
import com.Shubham.project.uber.uberApp.entities.Payment;
import com.Shubham.project.uber.uberApp.entities.enums.PaymentStatus;
import com.Shubham.project.uber.uberApp.entities.enums.TransactionMethod;
import com.Shubham.project.uber.uberApp.repositories.PaymentRepository;
import com.Shubham.project.uber.uberApp.services.PaymentService;
import com.Shubham.project.uber.uberApp.services.WalletService;
import com.Shubham.project.uber.uberApp.strategies.PaymentStrategy;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// RIder -> 100 ride amount
// Driver -> 70(for driver) Deduct 30Rs(Platform charge) from Driver Wallet

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platfromCommission = payment.getAmount() * PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(), platfromCommission, null, payment.getRide(),
                TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }

}