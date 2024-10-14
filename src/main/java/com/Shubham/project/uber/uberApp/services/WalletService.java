package com.Shubham.project.uber.uberApp.services;

import com.Shubham.project.uber.uberApp.entities.Ride;
import com.Shubham.project.uber.uberApp.entities.User;
import com.Shubham.project.uber.uberApp.entities.Wallet;
import com.Shubham.project.uber.uberApp.entities.enums.TransactionMethod;
import com.Shubham.project.uber.uberApp.entities.enums.TransactionType;

public interface WalletService {
        Wallet addMoneyToWallet(User user, double amount, String transactionId, Ride ride,
                        TransactionMethod transactionMethod);

        Wallet deductMoneyFromWallet(User user, double amount, String transactionId, Ride ride,
                        TransactionMethod transactionMethod);

        // this service for Driver only
        void withdrawAllMoneyFromWallet(User user, double amount);

        Wallet findWalletById(Long walletId);

        Wallet createNewWallet(User user);

        Wallet findByUser(User user);
}
