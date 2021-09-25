package com.harry.store.service;

import com.harry.store.dto.UserDto;
import com.harry.store.exception.UsernameAlreadyExistException;
import com.harry.store.model.Role;
import com.harry.store.model.User;
import com.harry.store.repository.UserRepository;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultUserService implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDto userDto) throws UsernameAlreadyExistException {

        if(userRepository.findByEmail(userDto.getEmail()).isPresent())
            throw new UsernameAlreadyExistException("User already exist with email: "+userDto.getEmail());

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        List<Role> roles = new ArrayList();
        roles.add(new Role(2,"USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }


}
