package com.authorizationservice.authorization;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authorizationservice.authorization.dto.UserDTO;
import com.authorizationservice.authorization.model.User;

@FeignClient(name = "user-client", url = "${User.URL}")
public interface UserClient {
		
	@PostMapping(value = "/signup")
    public User signup(@RequestBody UserDTO userDTO);

}
