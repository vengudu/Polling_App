package com.polling.restaurant.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.polling.restaurant.entity.UserInfo;
import com.polling.restaurant.repository.UserInfoRepository;

/**
*
* @author venkat
*/

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByUserName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
    
    public UserInfo addUser(UserInfo userInfo) throws Exception{
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo = repository.save(userInfo);
        return userInfo;
    }
}
