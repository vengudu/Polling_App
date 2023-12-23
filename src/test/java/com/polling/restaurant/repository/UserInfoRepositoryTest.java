package com.polling.restaurant.repository;

import com.polling.restaurant.entity.UserInfo;
import com.polling.restaurant.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserInfoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    void testFindByUserName() {
        // Arrange
        UserInfo user1 = new UserInfo("user1", "user1@example.com", "password123", "ROLE_USER");
        UserInfo user2 = new UserInfo("user2", "user2@example.com", "password456", "ROLE_ADMIN");

        entityManager.persistAndFlush(user1);
        entityManager.persistAndFlush(user2);

        // Act
        Optional<UserInfo> foundUser1 = userInfoRepository.findByUserName("user1");
        Optional<UserInfo> foundUser2 = userInfoRepository.findByUserName("user2");
        Optional<UserInfo> notFoundUser = userInfoRepository.findByUserName("nonexistentUser");

        // Assert
        assertTrue(foundUser1.isPresent());
        assertEquals("user1", foundUser1.get().getUserName());
        assertEquals("user1@example.com", foundUser1.get().getEmail());
        assertEquals("password123", foundUser1.get().getPassword());
        assertEquals("ROLE_USER", foundUser1.get().getRoles());

        assertTrue(foundUser2.isPresent());
        assertEquals("user2", foundUser2.get().getUserName());
        assertEquals("user2@example.com", foundUser2.get().getEmail());
        assertEquals("password456", foundUser2.get().getPassword());
        assertEquals("ROLE_ADMIN", foundUser2.get().getRoles());

        assertFalse(notFoundUser.isPresent());
    }
}
