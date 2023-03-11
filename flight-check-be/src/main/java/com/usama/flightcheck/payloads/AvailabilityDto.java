package com.usama.flightcheck.payloads;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AvailabilityDto {
	private Long id;

    private LocalDate date;
    private int seatsAvailable;
    private double fare;

}
