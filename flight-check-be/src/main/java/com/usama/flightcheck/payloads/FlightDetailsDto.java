package com.usama.flightcheck.payloads;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FlightDetailsDto {
	
	private Long id;
	private String flightNumber;
	private String departureCity;
	private LocalTime departureTime;
	private String arrivalCity;
	private LocalTime arrivalTime;
	private String airlines;

    private List<AvailabilityDto> flightDates = new ArrayList<>();

}
