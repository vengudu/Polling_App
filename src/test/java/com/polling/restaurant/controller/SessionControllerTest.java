package com.polling.restaurant.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.polling.restaurant.entity.Options;
import com.polling.restaurant.entity.PollSession;
import com.polling.restaurant.services.PollSessionService;

@ExtendWith(MockitoExtension.class)
public class SessionControllerTest {

    @Mock
    private PollSessionService sessionService;

    @InjectMocks
    private SessionController sessionController;

    @Test
    public void testHome() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");

        sessionController.home(model, principal);

        // Add your assertions here
    }

    @Test
    public void testActivateSession() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");

        sessionController.activateSession(model, principal);

        // Add your assertions here
    }

    @Test
    public void testGetActiveSession() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");

        sessionController.getActiveSession(model, principal);

        // Add your assertions here
    }

    @Test
    public void testDeactivateSession() {
        PollSession pollSession = new PollSession();
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");

        when(sessionService.deactivatePollSession()).thenReturn("Deactivated Successfully");

        sessionController.deactivateSession(pollSession, redirectAttributes, principal);

        // Add your assertions here
    }

    @Test
    public void testGetSelectedOption() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");

        sessionController.getSelectedOption(model, principal);

        // Add your assertions here
    }

    @Test
    public void testCreateSession() {
        PollSession pollSession = new PollSession();
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");

        when(sessionService.createPollSession(any(PollSession.class))).thenReturn("Created Successfully");

        sessionController.createSession(pollSession, redirectAttributes, principal);

        // Add your assertions here
    }

    @Test
    public void testPolling() throws Exception {
        Options options = new Options();
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");

        when(sessionService.polling(any(Options.class))).thenReturn("Polling Successfully");

        sessionController.polling(options, redirectAttributes, principal);

        // Add your assertions here
    }
}
