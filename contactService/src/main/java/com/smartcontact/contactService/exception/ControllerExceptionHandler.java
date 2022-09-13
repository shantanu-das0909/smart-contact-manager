// global exception handler

package com.smartcontact.contactService.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.smartcontact.contactService.model.ErrorMessage;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorMessage> InvalidTokenExceptionHandling(InvalidTokenException e, WebRequest request){
		
		log.info("Inside Invalid token exception handler");
		
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(),new Date(), "Token is either expired or invalid...", request.getDescription(false));
		
		log.info("Exiting Invalid token exception handler");
		
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorMessage> feignExceptionHandling(FeignException e, WebRequest request){
		
		log.info("Inside Invalid token exception handler");
		
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(),new Date(), "Token is either expired or invalid...", request.getDescription(false));
		
		log.info("Exiting Invalid token exception handler");
		
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
}
