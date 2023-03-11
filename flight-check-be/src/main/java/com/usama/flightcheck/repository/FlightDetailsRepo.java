package com.usama.flightcheck.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usama.flightcheck.entity.Availability;
import com.usama.flightcheck.entity.FlightDetails;

public interface FlightDetailsRepo extends JpaRepository<FlightDetails, Long> {
	List<Availability> findByFlightDates(Long id);
}
