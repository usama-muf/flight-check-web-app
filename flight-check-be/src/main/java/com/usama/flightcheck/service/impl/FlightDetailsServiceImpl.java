package com.usama.flightcheck.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usama.flightcheck.entity.FlightDetails;
import com.usama.flightcheck.exceptoin.ResourceNotFoundExceptoion;
import com.usama.flightcheck.payloads.FlightDetailsDto;
import com.usama.flightcheck.repository.FlightDetailsRepo;
import com.usama.flightcheck.service.FlightDetailsService;

@Service
public class FlightDetailsServiceImpl implements FlightDetailsService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FlightDetailsRepo flightDetailsRepo;

	@Override
	public FlightDetailsDto createFlightDetail(FlightDetailsDto flightDetailsDto) {

		FlightDetails flightDetails = this.modelMapper.map(flightDetailsDto, FlightDetails.class);
		FlightDetails newFlightDetails = this.flightDetailsRepo.save(flightDetails);
		return this.modelMapper.map(newFlightDetails, FlightDetailsDto.class);

	}

	@Override
	public FlightDetailsDto updateFlightDetail(Long id, FlightDetailsDto flightDetailsDto) {

		return null;
	}

	@Override
	public void deleteFlightDetail(FlightDetailsDto flightDetailsDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public FlightDetailsDto listFlightDetailsById(Long id) {
		FlightDetails details = this.flightDetailsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptoion("id", "" + id, "listFlightDetailsById"));
		return this.modelMapper.map(details, FlightDetailsDto.class);
	}

	@Override
	public List<FlightDetailsDto> listAllFlightDetails() {
		
		List<FlightDetails> listFlightDetails = this.flightDetailsRepo.findAll();
		List<FlightDetailsDto> listFlightDetailsDto = listFlightDetails.stream()
				.map((result)-> this.modelMapper.map(result, FlightDetailsDto.class)).collect(Collectors.toList());
		
		return listFlightDetailsDto;
	}

}
