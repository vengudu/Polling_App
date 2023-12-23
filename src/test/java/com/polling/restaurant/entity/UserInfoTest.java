package com.polling.restaurant.entity;

import com.polling.restaurant.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserInfoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveAndRetrieveUserInfo() {
        // Arrange
        UserInfo userInfo = new UserInfo("testUser", "test@example.com", "password123", "ROLE_USER");

        // Act
        UserInfo savedUserInfo = entityManager.persistAndFlush(userInfo);

        // Assert
        assertNotNull(savedUserInfo.getId());
        assertEquals("testUser", savedUserInfo.getUserName());
        assertEquals("test@example.com", savedUserInfo.getEmail());
        assertEquals("password123", savedUserInfo.getPassword());
        assertEquals("ROLE_USER", savedUserInfo.getRoles());
    }

    @Test
    void testGetterAndSetterMethods() {
        // Arrange
        UserInfo userInfo = new UserInfo();

        // Act
        userInfo.setId(1);
        userInfo.setUserName("testUser");
        userInfo.setEmail("test@example.com");
        userInfo.setPassword("password123");
        userInfo.setRoles("ROLE_USER");

        // Assert
        assertEquals(1, userInfo.getId());
        assertEquals("testUser", userInfo.getUserName());
        assertEquals("test@example.com", userInfo.getEmail());
        assertEquals("password123", userInfo.getPassword());
        assertEquals("ROLE_USER", userInfo.getRoles());
    }
}
