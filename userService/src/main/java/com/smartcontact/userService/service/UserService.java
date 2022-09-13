package com.smartcontact.userService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smartcontact.userService.dto.UserDTO;
import com.smartcontact.userService.model.AuthenticationRequest;
import com.smartcontact.userService.model.User;
import com.smartcontact.userService.repository.AuthRequestRepo;
import com.smartcontact.userService.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthRequestRepo authRequestRepo;

	//add a user
	public ResponseEntity<User> addUser(UserDTO userDTO) {
		
		log.info("inside addUser method in userService");
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setName(userDTO.getName());
		user.setUserEmail(userDTO.getUserEmail());
		user.setUserPhone(userDTO.getUserPhone());
		
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setUsername(userDTO.getUsername());
		authenticationRequest.setPassword(userDTO.getPassword());
		
		userRepository.save(user);
		authRequestRepo.save(authenticationRequest);
		
		log.info("inside addUser method in UserController: END");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	//get user by userId
	public ResponseEntity<User> getUser(int userId) {
		log.info("inside getUser method in userService");

		User user = userRepository.findById(userId).get();

		log.info("inside getUser method in UserController: END");

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	//get user by userName
	public ResponseEntity<User> getUserByUserName(String username) {
		log.info("inside getUserByUserName method in userService");

		User user = userRepository.getUserByUserName(username);
		
		log.info("inside getUserByUserName method in UserController: END");

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
