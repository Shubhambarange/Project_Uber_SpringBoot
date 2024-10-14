package com.Shubham.project.uber.uberApp.repositories;

import com.Shubham.project.uber.uberApp.entities.Payment;
import com.Shubham.project.uber.uberApp.entities.Ride;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByRide(Ride ride);
}
