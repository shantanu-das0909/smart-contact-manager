package com.smartcontact.contactService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcontact.contactService.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

	// for fetching all the contacts using userId from contact table
	@Query("SELECT m from Contact m WHERE m.user.id = :n")
	public List<Contact> getAllContactBelongsToAUser(@Param("n") int userId);
}
