package com.polling.restaurant.config;

import com.polling.restaurant.config.SecurityConfig;
import com.polling.restaurant.services.UserInfoUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    @Mock
    private UserInfoUserDetailsService userDetailsService;

    @InjectMocks
    private SecurityConfig securityConfig;

    @Test
    void testSecurityFilterChain() throws Exception {
        // Arrange
        MockitoAnnotations.initMocks(this);
        HttpSecurity http = mock(HttpSecurity.class);

        // Act
        SecurityFilterChain securityFilterChain = securityConfig.securityFilterChain(http);

        // Assert
        assertNotNull(securityFilterChain);
        verify(http, times(1)).csrf(any());
        verify(http, times(1)).authorizeHttpRequests(any());
        verify(http, times(1)).httpBasic(any());
    }

    @Test
    void testPasswordEncoder() {
        // Arrange
        MockitoAnnotations.initMocks(this);

        // Act
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        // Assert
        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);
    }


}
