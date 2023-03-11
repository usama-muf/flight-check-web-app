package com.usama.flightcheck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usama.flightcheck.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

	 Optional<User> findByEmailId(String username);
 
}
