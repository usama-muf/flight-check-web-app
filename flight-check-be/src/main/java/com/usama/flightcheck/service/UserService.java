package com.usama.flightcheck.service;

import java.util.List;

import com.usama.flightcheck.payloads.UserDto;
import com.usama.flightcheck.payloads.UserResponse;

public interface UserService {
	
	UserResponse createUser(UserDto userDto);
	List<UserResponse> listAllUsers();
	void deleteUser(Long id);
	UserResponse updateUser(Long id, UserDto userDto);

}
