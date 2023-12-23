package com.polling.restaurant.repository;

import com.polling.restaurant.entity.PollSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 *
 * @author venkat
 */
public interface PollSessionRepository extends JpaRepository<PollSession, Long> {

	PollSession findByIsActive(boolean active);
	List<PollSession> findAllByOrderByCreatedDateDesc();
}
