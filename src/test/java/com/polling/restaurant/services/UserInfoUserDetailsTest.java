package com.polling.restaurant.services;

import com.polling.restaurant.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserInfoUserDetailsTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        UserInfo userInfo = new UserInfo("user1", "user1@example.com", "password123", "ROLE_USER,ROLE_ADMIN");

        // Act
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Assert
        assertEquals(userInfo.getUserName(), userDetails.getUsername());
        assertEquals(userInfo.getPassword(), userDetails.getPassword());

        List<GrantedAuthority> expectedAuthorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        Collection<? extends GrantedAuthority> actualAuthorities = userDetails.getAuthorities();
        assertTrue(actualAuthorities.containsAll(expectedAuthorities));
        assertTrue(expectedAuthorities.containsAll(actualAuthorities));
    }

    @Test
    void testIsAccountNonExpired() {
        // Arrange
        UserInfo userInfo = new UserInfo("user1", "user1@example.com", "password123", "ROLE_USER,ROLE_ADMIN");
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        boolean result = userDetails.isAccountNonExpired();

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsAccountNonLocked() {
        // Arrange
        UserInfo userInfo = new UserInfo("user1", "user1@example.com", "password123", "ROLE_USER,ROLE_ADMIN");
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        boolean result = userDetails.isAccountNonLocked();

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsCredentialsNonExpired() {
        // Arrange
        UserInfo userInfo = new UserInfo("user1", "user1@example.com", "password123", "ROLE_USER,ROLE_ADMIN");
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        boolean result = userDetails.isCredentialsNonExpired();

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsEnabled() {
        // Arrange
        UserInfo userInfo = new UserInfo("user1", "user1@example.com", "password123", "ROLE_USER,ROLE_ADMIN");
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        boolean result = userDetails.isEnabled();

        // Assert
        assertTrue(result);
    }
}
