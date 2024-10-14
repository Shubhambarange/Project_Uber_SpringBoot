package com.Shubham.project.uber.uberApp.repositories;

import com.Shubham.project.uber.uberApp.entities.User;
import com.Shubham.project.uber.uberApp.entities.Wallet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUser(User user);
}
