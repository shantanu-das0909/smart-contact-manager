package com.smartcontact.userService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.smartcontact.userService.dto.VaildatingDTO;




@FeignClient(name = "auth-client", url = "${Authorization.URL}")
public interface AuthClient {
	
	 @GetMapping(value = "/validate")
     public VaildatingDTO getsValidity(@RequestHeader(name = "Authorization", required = true) String token);

}
