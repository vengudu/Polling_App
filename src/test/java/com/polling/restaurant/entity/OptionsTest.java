package com.polling.restaurant.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class OptionsTest {

    @Autowired
    private final TestEntityManager entityManager;

    OptionsTest(TestEntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Test
    void testSaveAndRetrieveOptions() {
        // Arrange
        PollSession pollSession = new PollSession(false,"",new Date(System.currentTimeMillis()));
        entityManager.persist(pollSession);

        Options options = new Options("Option1", "User1", pollSession);

        // Act
        Options savedOptions = entityManager.persistAndFlush(options);

        // Assert
        assertNotNull(savedOptions.getId());
        assertEquals("Option1", savedOptions.getOption());
        assertEquals("User1", savedOptions.getUserName());
        assertEquals(pollSession, savedOptions.getSession());
    }

    @Test
    void testGetterAndSetterMethods() {
        // Arrange
        Options options = new Options("","",new PollSession(false,"",new Date(System.currentTimeMillis())));

        // Act
        options.setId(1L);
        options.setOption("Option1");
        options.setUserName("User1");
        options.setSelected(true);

        // Assert
        assertEquals(1L, options.getId());
        assertEquals("Option1", options.getOption());
        assertEquals("User1", options.getUserName());
    }
}
