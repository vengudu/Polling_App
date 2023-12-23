package com.polling.restaurant.controller;


import com.polling.restaurant.entity.Options;
import com.polling.restaurant.entity.PollSession;
import com.polling.restaurant.services.PollSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.sql.Date;

/**
 *
 * @author venkat
 */
@Controller
@ComponentScan
public class SessionController {

    @Autowired
    private PollSessionService sessionService;
    
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String home(Model model, Principal principal) {
    	
    	model.addAttribute("loggedinUser",principal.getName());
     	model.addAttribute("isSessionUser", sessionService.getActivePollSession() != null && sessionService.getActivePollSession().getUserName().equalsIgnoreCase(principal.getName()));
    	model.addAttribute("isActive",sessionService.getActivePollSession() != null ? sessionService.getActivePollSession().getIsActive(): false); 	
    	
    	return "index";
    }

    @GetMapping("/activateSession")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String activateSession(Model model,Principal principal) {
    	model.addAttribute("loggedinUser",principal.getName());
    	model.addAttribute("isSessionUser", sessionService.getActivePollSession() != null && sessionService.getActivePollSession().getUserName().equalsIgnoreCase(principal.getName()));
    	model.addAttribute("pollsession",sessionService.getActivePollSession() != null ? sessionService.getActivePollSession() : new PollSession());
    	model.addAttribute("isActive",sessionService.getActivePollSession() != null ? sessionService.getActivePollSession().getIsActive(): false);
    	return "activateSession";
    }
    
    @GetMapping("/getActiveSession")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String getActiveSession(Model model,Principal principal) {
    	model.addAttribute("loggedinUser",principal.getName());
    	model.addAttribute("options",new Options(null, null, null));
    	model.addAttribute("isSessionUser", sessionService.getActivePollSession() != null && sessionService.getActivePollSession().getUserName().equalsIgnoreCase(principal.getName()));
    	
    	model.addAttribute("activepoll",sessionService.getActivePollSession() != null ? sessionService.getActivePollSession(): null);
    	model.addAttribute("optionsDetails",sessionService.getActivePollSession() != null ? sessionService.getActivePollSession().getOptions(): null);
         
    	return "pollingList";
    }

    @GetMapping("/deactivateSession")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String deactivateSession(@ModelAttribute("pollsession") PollSession pollSession, RedirectAttributes redirectr,Principal principal) {
    	redirectr.addFlashAttribute("loggedinUser",principal.getName());
    	redirectr.addFlashAttribute("randomResult",sessionService.deactivatePollSession());
       redirectr.addFlashAttribute("status","Session Deactivated Successfully");
       return "redirect:/activateSession";

    }

    @GetMapping("/getSelectedOption")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getSelectedOption(Model model, Principal principal) {
    	model.addAttribute("loggedinUser",principal.getName());
    	model.addAttribute("selectedResult",sessionService.getSelectedOption());
       return "selectedRestaurant";

    }
    
    @PostMapping("/createSession")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String createSession(@ModelAttribute("pollsession") PollSession pollSession,RedirectAttributes redirectr, Principal principal) {
    	String status = "";
    	pollSession.setIsActive(true);
    	redirectr.addFlashAttribute("loggedinUser",principal.getName());
        pollSession.setUserName(principal.getName());
        pollSession.setCreatedDate(new Date(System.currentTimeMillis()));
    	status = sessionService.createPollSession(pollSession);
    	
    	redirectr.addFlashAttribute("isActive",pollSession.getIsActive());
    	redirectr.addFlashAttribute("status",status);
    	return "redirect:/activateSession";
    }

    @PostMapping("/poll")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String polling(@ModelAttribute("options") Options options, RedirectAttributes redirectr,Principal principal) throws Exception {
        options.setUserName(principal.getName());
    	redirectr.addFlashAttribute("loggedinUser",principal.getName());
    	redirectr.addFlashAttribute("status",sessionService.polling(options));
        
        return "redirect:/getActiveSession";
    }
}
