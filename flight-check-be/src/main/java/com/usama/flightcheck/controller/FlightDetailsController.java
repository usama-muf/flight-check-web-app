package com.usama.flightcheck.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usama.flightcheck.payloads.FlightDetailsDto;
import com.usama.flightcheck.service.FlightDetailsService;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightDetailsController {
	
	@Autowired 
	private FlightDetailsService flightDetailsService;
	
	
	

	@GetMapping("/allflights") 
	public ResponseEntity<List<FlightDetailsDto>> listAllFlightDetails() {
		List<FlightDetailsDto> details = flightDetailsService.listAllFlightDetails();
	return new ResponseEntity<List<FlightDetailsDto>>(details, HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FlightDetailsDto> listFlightDetailsById(@PathVariable Long id) {
		return ResponseEntity.ok(this.flightDetailsService.listFlightDetailsById(id));
	}
	
	
	@PostMapping("/addflight")
	public ResponseEntity<FlightDetailsDto> postData(@RequestBody FlightDetailsDto flightDetailsDto) {
		FlightDetailsDto details = this.flightDetailsService.createFlightDetail(flightDetailsDto);
		return new ResponseEntity<FlightDetailsDto>(details, HttpStatus.OK);
	}
	
	
	
}
