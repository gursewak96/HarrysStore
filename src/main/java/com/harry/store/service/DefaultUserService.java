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
import java.util.Optional;

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
        if(userDto.getRoles() == null){
            roles.add(new Role(2,"USER"));
        }else{
            roles = userDto.getRoles();
        }

        System.out.println(roles);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
       Optional<User> user = userRepository.findById(id);
       if(!user.isPresent())
           throw new UsernameNotFoundException("User don't exist with id: "+id);

       userRepository.delete(user.get());
    }


}
