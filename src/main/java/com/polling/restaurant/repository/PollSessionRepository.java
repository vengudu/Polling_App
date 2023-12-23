package com.polling.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polling.restaurant.entity.PollSession;


/**
 *
 * @author venkat
 */
public interface PollSessionRepository extends JpaRepository<PollSession, Long> {

	PollSession findByIsActive(boolean active);
	List<PollSession> findAllByOrderByCreatedDateDesc();
}
