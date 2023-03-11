package com.usama.flightcheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usama.flightcheck.exceptoin.BadCredentialsException;
import com.usama.flightcheck.payloads.AuthenticationRequest;
import com.usama.flightcheck.payloads.AuthenticationResponse;
import com.usama.flightcheck.security.CustomUserDetailService;
import com.usama.flightcheck.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService customUserDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) {
			
		try { 
			this.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch (Exception e) {
			throw new BadCredentialsException(); 
		}

		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken(token);

		System.out.println("\nToken Generated Successfully: "+response.getToken());
		return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);

	}

	public void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException();
		}

	}

}
