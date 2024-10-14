package com.Shubham.project.uber.uberApp.services.impl;

import com.Shubham.project.uber.uberApp.entities.Ride;
import com.Shubham.project.uber.uberApp.entities.User;
import com.Shubham.project.uber.uberApp.entities.Wallet;
import com.Shubham.project.uber.uberApp.entities.WalletTransaction;
import com.Shubham.project.uber.uberApp.entities.enums.TransactionMethod;
import com.Shubham.project.uber.uberApp.entities.enums.TransactionType;
import com.Shubham.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.Shubham.project.uber.uberApp.repositories.WalletRepository;
import com.Shubham.project.uber.uberApp.services.WalletService;
import com.Shubham.project.uber.uberApp.services.WalletTransectionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransectionService walletTransectionService;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, double amount, String transactionId, Ride ride,
            TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to add must be greater than zero.");
        }

        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        // walletTransectionService.createNewWalletTransection(walletTransaction);
        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, double amount, String transactionId, Ride ride,
            TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to deduct must be greater than zero.");
        }
        if (wallet.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds in wallet.");
        }

        wallet.setBalance(wallet.getBalance() - amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransectionService.createNewWalletTransection(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMoneyFromWallet(User user, double amount) {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(
                () -> new ResourceNotFoundException("Wallet Not Found With id " + walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {

        return walletRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("Wallet not found fro user " + user.getId()));
    }

}
