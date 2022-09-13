package com.smartcontact.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcontact.userService.AuthClient;
import com.smartcontact.userService.dto.UserDTO;
import com.smartcontact.userService.exception.InvalidTokenException;
import com.smartcontact.userService.model.User;
import com.smartcontact.userService.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthClient authClient;
	
	private String msg = "Token is either expired or invalid...";
	
	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO){
		log.info("inside addUser method in UserController: BEGIN");
		return userService.addUser(userDTO);
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
		
		log.info(token);
		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}
		log.info("inside getUser method in UserController: BEGIN");
		return userService.getUser(userId);
	}
	
	@GetMapping("/getUserByUsername/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
	
		log.info(token);
		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}
		log.info("inside getUserByUserName method in UserController: BEGIN");

		return userService.getUserByUserName(username);
	}
}
