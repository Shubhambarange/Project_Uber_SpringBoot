package com.Shubham.project.uber.uberApp.dto;

import com.Shubham.project.uber.uberApp.entities.WalletTransaction;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {
    private Long id;

    private UserDto user;

    private Double balance;

    private List<WalletTransactionDto> transactions;
}
