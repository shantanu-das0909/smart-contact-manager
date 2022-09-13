package com.smartcontact.userService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	

	private String username;

	private String password;
	
	private String name;

	private String userEmail;

	private String userPhone;
}
