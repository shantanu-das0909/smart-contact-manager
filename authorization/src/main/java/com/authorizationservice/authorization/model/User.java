package com.authorizationservice.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int userID;

	private String username;

	private String name;

	private String userEmail;

	private String userPhone;

}
