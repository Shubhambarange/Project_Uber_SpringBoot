package com.Shubham.project.uber.uberApp.services;

import com.Shubham.project.uber.uberApp.entities.Payment;
import com.Shubham.project.uber.uberApp.entities.Ride;
import com.Shubham.project.uber.uberApp.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePayemntStatus(Payment payment, PaymentStatus paymentStatus);

}
