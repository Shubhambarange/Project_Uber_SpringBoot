package com.Shubham.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.Shubham.project.uber.uberApp.entities.enums.PaymentStatus;
import com.Shubham.project.uber.uberApp.entities.enums.TransactionMethod;
import com.Shubham.project.uber.uberApp.repositories.PaymentRepository;
import com.Shubham.project.uber.uberApp.entities.Driver;
import com.Shubham.project.uber.uberApp.entities.Payment;
import com.Shubham.project.uber.uberApp.entities.Rider;
import com.Shubham.project.uber.uberApp.entities.Wallet;
import com.Shubham.project.uber.uberApp.services.PaymentService;
import com.Shubham.project.uber.uberApp.services.WalletService;
import com.Shubham.project.uber.uberApp.strategies.PaymentStrategy;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Rider had1 232, Driver had 500
// Ride cost is 100, comission = 30
// Rider -> 232 - 100 = 132 
// Driver -> 500 + (100 - 30) = 570

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(),
                null, payment.getRide(), TransactionMethod.RIDE);

        double driverCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(),
                driverCut, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }

}