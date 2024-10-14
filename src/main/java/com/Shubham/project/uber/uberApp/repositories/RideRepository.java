package com.Shubham.project.uber.uberApp.repositories;

import com.Shubham.project.uber.uberApp.entities.Driver;
import com.Shubham.project.uber.uberApp.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Shubham.project.uber.uberApp.entities.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {


    //    This methods is Automaticaly Created By the Spring boot
    Page<Ride> findByRider(Rider rider, Pageable pageRequest);

    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);
}
