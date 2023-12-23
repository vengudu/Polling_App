package com.polling.restaurant.repository;

import com.polling.restaurant.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
*
* @author venkat
*/
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUserName(String username);

}
