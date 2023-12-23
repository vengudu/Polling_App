package com.polling.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polling.restaurant.entity.UserInfo;
import com.polling.restaurant.response.ErrorResponse;
import com.polling.restaurant.services.UserInfoUserDetailsService;

/**
*
* @author venkat
*/

@RestController
@RequestMapping("/v1/users")
public class UserController {

	private static final String INVALID_INPUT = "Invalid Input";
	private static final String SERVER_ERROR = "Server Error";
	
    @Autowired
    private UserInfoUserDetailsService service;

    @PostMapping("/new")
    public ResponseEntity<?> addNewUser(@RequestBody UserInfo userInfo){
    	
    	try{
    		UserInfo savedUserInfo =	service.addUser(userInfo);
    		return ResponseEntity.status(200).body(savedUserInfo);
    	}catch(DataIntegrityViolationException e ) {
    		ErrorResponse error = new ErrorResponse(INVALID_INPUT, INVALID_INPUT);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    	}catch(Exception e ) {
    		ErrorResponse error = new ErrorResponse(SERVER_ERROR, e.getLocalizedMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
    }
}
    