package com.harry.store.service;

import com.harry.store.dto.UserDto;
import com.harry.store.exception.UsernameAlreadyExistException;

public interface UserService {
    public void register(UserDto userDto) throws UsernameAlreadyExistException;
}
