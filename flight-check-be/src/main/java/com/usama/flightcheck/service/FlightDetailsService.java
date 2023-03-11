package com.usama.flightcheck.service;

import java.util.List;

import com.usama.flightcheck.payloads.FlightDetailsDto;

public interface FlightDetailsService {
	
	FlightDetailsDto createFlightDetail(FlightDetailsDto flightDetailsDto);
	FlightDetailsDto updateFlightDetail(Long id, FlightDetailsDto flightDetailsDto);
	void deleteFlightDetail(FlightDetailsDto flightDetailsDto);
	FlightDetailsDto listFlightDetailsById(Long id);
	
	//Later use pages for efficient listings
	List<FlightDetailsDto> listAllFlightDetails();
	
	
	
}
