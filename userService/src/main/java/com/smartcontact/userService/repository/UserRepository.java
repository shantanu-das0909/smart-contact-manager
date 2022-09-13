package com.smartcontact.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smartcontact.userService.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "select c from User c where c.username=?1")
	public User getUserByUserName(String userName);
}
