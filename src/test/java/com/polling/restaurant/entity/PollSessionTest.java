package com.polling.restaurant.entity;

import com.polling.restaurant.entity.Options;
import com.polling.restaurant.entity.PollSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PollSessionTest {

	private static PollSession pollSession;

	@BeforeAll
	public static void setUp() {
		pollSession = new PollSession(false,"user",new Date(System.currentTimeMillis()));
	}

	@Test
	public void testGetSetId() {
		Long id = 1L;
		pollSession.setId(id);
		assertEquals(id, pollSession.getId());
	}

	@Test
	public void testGetSetSessionName() {
		String sessionName = "TestSession";
		pollSession.setSessionName(sessionName);
		assertEquals(sessionName, pollSession.getSessionName());
	}

	@Test
	public void testGetSetUserName() {
		String userName = "TestUser";
		pollSession.setUserName(userName);
		assertEquals(userName, pollSession.getUserName());
	}

	@Test
	public void testGetSetCreatedDate() {
		Date createdDate = new Date(System.currentTimeMillis());
		pollSession.setCreatedDate(createdDate);
		assertEquals(createdDate, pollSession.getCreatedDate());
	}

	@Test
	public void testGetSetOptions() {
		List<Options> options = new ArrayList<>();
		Options option1 = new Options();
		Options option2 = new Options();

		options.add(option1);
		options.add(option2);

		pollSession.setOptions(options);

		assertNotNull(pollSession.getOptions());
		assertEquals(options.size(), pollSession.getOptions().size());
	}

	@Test
	public void testGetSetIsActive() {
		Boolean isActive = true;
		pollSession.setIsActive(isActive);
		assertEquals(isActive, pollSession.getIsActive());
	}
}
