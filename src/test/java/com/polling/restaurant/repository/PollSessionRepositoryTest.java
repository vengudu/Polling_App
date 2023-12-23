package com.polling.restaurant.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.polling.restaurant.entity.PollSession;

@DataJpaTest
class PollSessionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PollSessionRepository pollSessionRepository;

    @Test
    void testFindByIsActive() {
        // Arrange
        PollSession activeSession = new PollSession(true, "User1", Date.valueOf("2023-01-01"));
        PollSession inactiveSession = new PollSession(false, "User2", Date.valueOf("2023-01-02"));

        entityManager.persistAndFlush(activeSession);
        entityManager.persistAndFlush(inactiveSession);

        // Act
        PollSession foundActiveSession = pollSessionRepository.findByIsActive(true);
        PollSession foundInactiveSession = pollSessionRepository.findByIsActive(false);

        // Assert
        assertNotNull(foundActiveSession);
        assertEquals("User1", foundActiveSession.getUserName());
        assertEquals(true, foundActiveSession.getIsActive());

        assertNotNull(foundInactiveSession);
        assertEquals("User2", foundInactiveSession.getUserName());
        assertEquals(false, foundInactiveSession.getIsActive());
    }

    @Test
    void testFindAllByOrderByCreatedDateDesc() {
        // Arrange
        PollSession session1 = new PollSession(true, "User1", Date.valueOf("2023-01-01"));
        PollSession session2 = new PollSession(true, "User1", Date.valueOf("2023-01-02"));

        entityManager.persistAndFlush(session1);
        entityManager.persistAndFlush(session2);

        // Act
        List<PollSession> sessions = pollSessionRepository.findAllByOrderByCreatedDateDesc();

        // Assert
        assertEquals(2, sessions.size());
        assertEquals(Date.valueOf("2023-01-02"), sessions.get(0).getCreatedDate());
        assertEquals(Date.valueOf("2023-01-01"), sessions.get(1).getCreatedDate());
    }
}
