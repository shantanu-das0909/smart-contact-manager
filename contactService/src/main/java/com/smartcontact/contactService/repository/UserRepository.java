package com.smartcontact.contactService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcontact.contactService.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
