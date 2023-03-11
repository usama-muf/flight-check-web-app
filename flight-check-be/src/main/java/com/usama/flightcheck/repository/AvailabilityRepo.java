package com.usama.flightcheck.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usama.flightcheck.entity.Availability;

public interface AvailabilityRepo extends JpaRepository<Availability, Long> {
	
}
