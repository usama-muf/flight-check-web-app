package com.usama.flightcheck.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usama.flightcheck.entity.User;
import com.usama.flightcheck.exceptoin.ResourceNotFoundExceptoion;
import com.usama.flightcheck.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("\nprint statement from custom userr details service "+username);
		User user = this.userRepo.findByEmailId(username).orElseThrow(()-> new ResourceNotFoundExceptoion("EmailId", username, "CustomUserDetailService"));
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
	            new ArrayList<>()); 
	}

	
}
