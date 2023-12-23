package com.polling.restaurant.services;

import com.polling.restaurant.entity.UserInfo;
import com.polling.restaurant.repository.UserInfoRepository;
import com.polling.restaurant.services.UserInfoUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserInfoUserDetailsServiceTest {

    @Mock
    private UserInfoRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserInfoUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        // Arrange
        String username = "user1";
        UserInfo userInfo = new UserInfo(username, "user1@example.com", "password123", "ROLE_USER");
        when(repository.findByUserName(username)).thenReturn(Optional.of(userInfo));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(userInfo.getUserName(), userDetails.getUsername());
        verify(repository, times(1)).findByUserName(username);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonexistentUser";
        when(repository.findByUserName(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
        verify(repository, times(1)).findByUserName(username);
    }

    
}
