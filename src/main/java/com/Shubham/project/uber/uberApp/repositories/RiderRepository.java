package com.Shubham.project.uber.uberApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Shubham.project.uber.uberApp.entities.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
}