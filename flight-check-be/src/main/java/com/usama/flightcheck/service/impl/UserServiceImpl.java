package com.usama.flightcheck.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.usama.flightcheck.entity.User;
import com.usama.flightcheck.exceptoin.ResourceNotFoundExceptoion;
import com.usama.flightcheck.payloads.UserDto;
import com.usama.flightcheck.payloads.UserResponse;
import com.usama.flightcheck.repository.UserRepo;
import com.usama.flightcheck.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserResponse createUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		User createdUser = this.userRepo.save(user);
		return this.modelMapper.map(createdUser, UserResponse.class);

	}

	@Override
	public List<UserResponse> listAllUsers() {

		List<User> allUsers = this.userRepo.findAll();
		List<UserResponse> allUserResponse = allUsers.stream().map((user) -> this.modelMapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
		return allUserResponse;
	}

	@Override
	public void deleteUser(Long id) {

		User user = findUserById(id);
		this.userRepo.delete(user);
		return;
	}

	@Override
	public UserResponse updateUser(Long id, UserDto userDto) {

		User user = findUserById(id);
		UserDto updatedUserDto = this.modelMapper.map(user, UserDto.class);
		updatedUserDto.setUsername(userDto.getUsername());
		updatedUserDto.setEmailId(userDto.getEmailId());
		updatedUserDto.setPassword(userDto.getPassword());

		User updatedUser = this.userRepo.save(this.modelMapper.map(updatedUserDto, User.class));
		return this.modelMapper.map(updatedUser, UserResponse.class);
	}

	private User findUserById(Long id) {
		User user = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptoion("userId", "" + id, "delteUser userService"));
		return user;

	}

}
