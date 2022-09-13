package com.smartcontact.contactService.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcontact.contactService.AuthClient;
import com.smartcontact.contactService.dto.ContactDTO;
import com.smartcontact.contactService.dto.ContactUpdateDTO;
import com.smartcontact.contactService.exception.InvalidTokenException;
import com.smartcontact.contactService.model.Contact;
import com.smartcontact.contactService.service.ContactService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ContactController {

	@Autowired
	private AuthClient authClient;

	@Autowired
	private ContactService contactService;

	private String msg = "Token is either expired or invalid...";

	// add a contact
	@PostMapping("/addContact")
	public ResponseEntity<Contact> addContact(@RequestBody ContactDTO conatctDto,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
		
		log.info("inside addContact method in ContactController class: BEGIN");
		log.info(token);
		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}
		return contactService.addContact(conatctDto);
	}
	
	
	// get all the contacts for a particular user
	@GetMapping("/getContacts/{userId}")
	public ResponseEntity<List<Contact>> getContacts(@PathVariable("userId") int userId,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
		
		log.info(token);
		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}
		log.info("inside getContacts method in ContactController: BEGIN");
		return contactService.getContacts(userId);
	}

	//get a single contact
	@GetMapping("/getContact/{contactId}")
	public ResponseEntity<Contact> getContact(@PathVariable("contactId") int contactId,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		log.info(token);
		
		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}
		log.info("inside getContact method in ContactController: BEGIN");

		return contactService.getContact(contactId);
	}

	//update a contact
	@PutMapping("/updateContact")
	public ResponseEntity<Contact> updateContact(@RequestBody ContactUpdateDTO contactUpdateDTO,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
		
		log.info(token);
		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}
		log.info("inside updateContact method in ContactController: BEGIN");

		return contactService.updateContact(contactUpdateDTO);
	}

	// delete a contact
	@DeleteMapping("/deleteContact/{contactId}")
	public ResponseEntity<Map<String, String>> deleteContact(@PathVariable("contactId") int contactId,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
		
		log.info(token);
		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}
		log.info("inside updateContact method in ContactController: BEGIN");
		contactService.deleteContact(contactId);
		return new ResponseEntity<>(Map.of("message", "Deleted Successfully"), HttpStatus.OK);
	}
}
