package com.Shubham.project.uber.uberApp.repositories;

import com.Shubham.project.uber.uberApp.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransectionRepository extends JpaRepository<WalletTransaction,Long> {
}
