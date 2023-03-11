package com.usama.flightcheck.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usama.flightcheck.payloads.ApiResponse;
import com.usama.flightcheck.payloads.UserDto;
import com.usama.flightcheck.payloads.UserResponse;
import com.usama.flightcheck.service.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

//	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	public String temp() {
		return "Hello";
	}
	
//	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/allusers")
	public ResponseEntity<List<UserResponse>> listAllUsers() {
		List<UserResponse> allUsers = this.userService.listAllUsers();
		return ResponseEntity.ok(allUsers);
	}

	@PostMapping("/newuser")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto) {
		UserResponse response = this.userService.createUser(userDto);
		return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully !"), HttpStatus.OK);

	}

	@PutMapping("updateuser/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		UserResponse response = this.userService.updateUser(id, userDto);
		return ResponseEntity.ok(response);
	}

}
