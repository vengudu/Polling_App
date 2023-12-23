package com.polling.restaurant.services;

import com.polling.restaurant.entity.Options;
import com.polling.restaurant.entity.PollSession;
import com.polling.restaurant.repository.OptionRepository;
import com.polling.restaurant.repository.PollSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class PollSessionServiceTest {

    @Mock
    private PollSessionRepository sessionRepository;

    @Mock
    private OptionRepository optionRepository;

    @InjectMocks
    private PollSessionService pollSessionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreatePollSession() {
        // Arrange
        PollSession session = new PollSession();
        when(sessionRepository.findByIsActive(true)).thenReturn(null);
        when(sessionRepository.save(session)).thenReturn(session);

        // Act
        String status = pollSessionService.createPollSession(session);

        // Assert
        assertEquals("Session Created successfully", status);
        verify(sessionRepository, times(1)).findByIsActive(true);
        verify(sessionRepository, times(1)).save(session);
    }

    @Test
    void testDeactivatePollSession() {
        // Arrange
        PollSession session = new PollSession(true, "User1", null);
        List<Options> optionsList = new ArrayList<>();
        Options option = new Options("Option1", "User1", session);
        optionsList.add(option);
        session.setOptions(optionsList);

        when(sessionRepository.findByIsActive(true)).thenReturn(session);
        when(optionRepository.save(any(Options.class))).thenReturn(option);

        // Act
        String result = pollSessionService.deactivatePollSession();

        // Assert
        assertEquals("The Selected Restaurant is : Option1", result);
        verify(sessionRepository, times(1)).findByIsActive(true);
        verify(optionRepository, times(1)).save(any(Options.class));
    }

    @Test
    void testGetSelectedOption() {
        // Arrange
        PollSession session = new PollSession(false, "User1", null);
        List<Options> optionsList = new ArrayList<>();
        Options option = new Options("Option1", "User1", session);
        optionsList.add(option);
        session.setOptions(optionsList);

        List<PollSession> sessionList = new ArrayList<>();
        sessionList.add(session);

        when(sessionRepository.findByIsActive(true)).thenReturn(null);
        when(sessionRepository.findAllByOrderByCreatedDateDesc()).thenReturn(sessionList);

        // Act
        String result = pollSessionService.getSelectedOption();

        // Assert
        assertEquals("The Selected Restaurant is : Option1", result);
        verify(sessionRepository, times(1)).findByIsActive(true);
        verify(sessionRepository, times(1)).findAllByOrderByCreatedDateDesc();
    }

    @Test
    void testGetActivePollSession() {
        // Arrange
        PollSession session = new PollSession(true, "User1", null);

        when(sessionRepository.findByIsActive(true)).thenReturn(session);

        // Act
        PollSession result = pollSessionService.getActivePollSession();

        // Assert
        assertEquals(session, result);
        verify(sessionRepository, times(1)).findByIsActive(true);
    }

    @Test
    void testPolling() throws Exception {
        // Arrange
        PollSession session = new PollSession(true, "User1", null);
        Options options = new Options("Option1", "User1", session);

        when(sessionRepository.findByIsActive(true)).thenReturn(session);
        when(optionRepository.save(any(Options.class))).thenReturn(options);

        // Act
        String result = pollSessionService.polling(options);

        // Assert
        assertEquals("Your polling is successful ", result);
        verify(sessionRepository, times(1)).findByIsActive(true);
        verify(optionRepository, times(1)).save(any(Options.class));
    }
}
