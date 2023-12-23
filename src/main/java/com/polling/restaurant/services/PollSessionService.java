package com.polling.restaurant.services;

import com.polling.restaurant.entity.Options;
import com.polling.restaurant.entity.PollSession;

import com.polling.restaurant.repository.PollSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author venkat
 */
@Service
public class PollSessionService {

	private static final String RESTAURANT_SELECTED = "The Selected Restaurant is : " ;
	private static final String NO_RESTAURANT_SELECTED = "There is no resaurant polled!";
	private static final String SESSION_ACTIVE = " Session is Still Active!";
	private static final String SESSION_CAREATED_SUCCESSFULLY = "Session Created successfully";
	private static final String SESSION_ACTIVE_ALREADY = 	"Sorry Already a Session is Active";
	private static final String NO_ACTIVE_POLLING = 	"There is no active polling!";
	private static final String POLL_ONLY_ONCE= 	"You can only poll once!";
	private static final String POLLING_SUCCESSFUL = 	"Your polling is successful ";
	
	
	@Autowired
	private PollSessionRepository sessionRepository;
	@Autowired
	private com.polling.restaurant.repository.OptionRepository optionRepository;

	@Autowired
	public PollSessionService() {
	}

	public String createPollSession(PollSession session) {
		
		String status = null;
		
		try {
			
			if(sessionRepository.findByIsActive(true) == null) {
			status = SESSION_CAREATED_SUCCESSFULLY;
			 
					session = sessionRepository.save(session);
					return session != null ? status : null;
		}else{
			
			status = SESSION_ACTIVE_ALREADY;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public String deactivatePollSession() throws NullPointerException {

		try {
			PollSession session = sessionRepository.findByIsActive(true);
			
			if (session != null) {
				session.setIsActive(false);
				if(!session.getOptions().isEmpty()) {
					session.getOptions().get(new Random().nextInt(session.getOptions().size())).setSelected(true);		
					sessionRepository.save(session);
					return RESTAURANT_SELECTED + session.getOptions().stream().filter(opt -> opt.isSelected() == true).findFirst().get().getOption();
				}
				else {
					
					sessionRepository.save(session);
					return NO_RESTAURANT_SELECTED;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSelectedOption() throws NullPointerException {

		String selectedRestaurant = "";
		
		try {
			if(sessionRepository.findByIsActive(true) != null) {
				return SESSION_ACTIVE;
			}else {
				
			List<PollSession> sessionList =  sessionRepository.findAllByOrderByCreatedDateDesc();
			
			
			if (sessionList != null) {

				if(!sessionList.stream().findFirst().get().getOptions().isEmpty()) {
					
					selectedRestaurant = sessionList.stream().findFirst().get().getOptions().stream().filter(opt -> opt.isSelected() == true).findFirst().get().getOption();
					
					return RESTAURANT_SELECTED + selectedRestaurant;
				}else{
					
					return NO_RESTAURANT_SELECTED;
				}
				
			}	
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PollSession getActivePollSession() throws NullPointerException {
		try {

			PollSession session = sessionRepository.findByIsActive(true);
			
			return session;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public String polling(Options options) throws Exception {
		try {

			PollSession session = sessionRepository.findByIsActive(true);
			String status = "";
			// To Do polling
			if (session != null && !session.getIsActive()) {
				status = NO_ACTIVE_POLLING;
				return status;
			}

			Optional<Options> alreadyAvlOption = session.getOptions().stream()
					.filter(object -> Objects.equals(object.getUserName(), options.getUserName())).findFirst();
			if (alreadyAvlOption.isPresent()) {

				status = POLL_ONLY_ONCE;
				return status;
			}
			optionRepository.save(new Options(options.getOption(), options.getUserName(), session));
			status = POLLING_SUCCESSFUL;
			return status;
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
}
