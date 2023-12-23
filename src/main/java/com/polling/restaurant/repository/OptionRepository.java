package com.polling.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polling.restaurant.entity.Options;

/**
 *
 * @author venkat
 */
public interface OptionRepository extends JpaRepository<Options, Long> {
 
	 List<Options> findAllByUserName(String userName);
	
}
