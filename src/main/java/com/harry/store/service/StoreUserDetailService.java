package com.harry.store.service;

import com.harry.store.model.StoreUserDetails;
import com.harry.store.model.User;
import com.harry.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(userName);
        user.orElseThrow(()->new UsernameNotFoundException("Not found: "+userName));
        return user.map(StoreUserDetails::new).get();
    }
}
