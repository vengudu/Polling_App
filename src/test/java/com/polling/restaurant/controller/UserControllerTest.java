package com.polling.restaurant.controller;

import com.polling.restaurant.entity.UserInfo;
import com.polling.restaurant.response.ErrorResponse;
import com.polling.restaurant.services.UserInfoUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserInfoUserDetailsService service;

    @InjectMocks
    private UserController userController;

    @Test
    public void testAddNewUser_Success()  throws Exception{
        UserInfo userInfo = new UserInfo();
        when(service.addUser(any(UserInfo.class))).thenReturn(userInfo);

        ResponseEntity<?> responseEntity = userController.addNewUser(userInfo);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userInfo, responseEntity.getBody());
    }

    @Test
    public void testAddNewUser_DataIntegrityViolationException()  throws Exception{
        UserInfo userInfo = new UserInfo();
        when(service.addUser(any(UserInfo.class))).thenThrow(new DataIntegrityViolationException("Duplicate key"));

        ResponseEntity<?> responseEntity = userController.addNewUser(userInfo);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid Input", ((ErrorResponse) responseEntity.getBody()).getMessage());
    }

    @Test
    public void testAddNewUser_GenericException() throws Exception{
        UserInfo userInfo = new UserInfo();
        when(service.addUser(any(UserInfo.class))).thenThrow(new RuntimeException("Some error occurred"));

        ResponseEntity<?> responseEntity = userController.addNewUser(userInfo);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Server Error", ((ErrorResponse) responseEntity.getBody()).getMessage());
    }
}
