package com.Shubham.project.uber.uberApp.services;

import com.Shubham.project.uber.uberApp.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.Shubham.project.uber.uberApp.dto.RideRequestDto;
import com.Shubham.project.uber.uberApp.entities.Driver;
import com.Shubham.project.uber.uberApp.entities.Ride;
import com.Shubham.project.uber.uberApp.entities.RideRequest;
import com.Shubham.project.uber.uberApp.entities.enums.RideStatus;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
