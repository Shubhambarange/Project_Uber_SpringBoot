package com.Shubham.project.uber.uberApp.strategies;

import org.springframework.stereotype.Component;

import com.Shubham.project.uber.uberApp.entities.enums.PaymentMethod;
import com.Shubham.project.uber.uberApp.strategies.impl.CashPaymentStrategy;
import com.Shubham.project.uber.uberApp.strategies.impl.WalletPaymentStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }

}
