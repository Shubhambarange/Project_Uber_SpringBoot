package com.Shubham.project.uber.uberApp.services.impl;

import com.Shubham.project.uber.uberApp.dto.WalletTransactionDto;
import com.Shubham.project.uber.uberApp.entities.WalletTransaction;
import com.Shubham.project.uber.uberApp.repositories.WalletTransectionRepository;
import com.Shubham.project.uber.uberApp.services.WalletTransectionService;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransectionServiceImpl implements WalletTransectionService {

    private final WalletTransectionRepository transectionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransection(WalletTransaction walletTransactionDto) {
        WalletTransaction walletTransaction = modelMapper.map(walletTransactionDto, WalletTransaction.class);
        transectionRepository.save(walletTransaction);
    }
}
