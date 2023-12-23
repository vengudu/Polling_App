package com.polling.restaurant.repository;

import com.polling.restaurant.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author venkat
 */
public interface OptionRepository extends JpaRepository<Options, Long> {
 
	 List<Options> findAllByUserName(String userName);
	
}
