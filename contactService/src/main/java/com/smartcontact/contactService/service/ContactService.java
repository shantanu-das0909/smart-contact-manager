package com.smartcontact.contactService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smartcontact.contactService.dto.ContactDTO;
import com.smartcontact.contactService.dto.ContactUpdateDTO;
import com.smartcontact.contactService.model.Contact;
import com.smartcontact.contactService.model.User;
import com.smartcontact.contactService.repository.ContactRepository;
import com.smartcontact.contactService.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// add a contact
	public ResponseEntity<Contact> addContact(ContactDTO contactDTO) {
		log.info("inside addContact in ContactService");

		Contact contact = new Contact();
		contact.setContactId(contactDTO.getContactId());
		contact.setContactName(contactDTO.getContactName());
		contact.setContactEmail(contactDTO.getContactEmail());
		contact.setContactPhone(contactDTO.getContactPhone());
		System.out.println(contactDTO);
		User user = userRepository.findById(contactDTO.getUserId()).get();
		System.out.println(user);
		contact.addUser(user);
		contactRepository.save(contact);
		
		log.info("inside addContact method in ContactController class: END");
		return new ResponseEntity<>(contact, HttpStatus.CREATED);
	}

	// get all the contacts for a single user
	public ResponseEntity<List<Contact>> getContacts(int userId) {
		log.info("inside getContacts methos in ContactService");
		
		List<Contact> contacts = contactRepository.getAllContactBelongsToAUser(userId);
		
		log.info("inside getContacts method in ContactController: END");
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}

	// update a contact
	public ResponseEntity<Contact> updateContact(ContactUpdateDTO contactUpdateDTO) {
		
		log.info("inside updateContact method in ContactService");
		
		Contact contact = contactRepository.findById(contactUpdateDTO.getContactId()).get();
		contact.setContactName(contactUpdateDTO.getContactName());
		contact.setContactEmail(contactUpdateDTO.getContactEmail());
		contact.setContactPhone(contactUpdateDTO.getContactPhone());
		contactRepository.save(contact);
		
		log.info("inside updateContact method in ContactController: END");
		
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}

	// delete a contact
	public void deleteContact(int contactId) {
		
		log.info("inside deleteContact method in ContactService");

		contactRepository.deleteById(contactId);
		
		log.info("inside deleteContact method in ContactController: END");

	}

	// get a single contact
	public ResponseEntity<Contact> getContact(int contactId) {
		
		log.info("inside getContact method in ContactService");

		Contact contact = contactRepository.findById(contactId).get();

		log.info("inside getContact method in ContactController: END");

		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}
}













