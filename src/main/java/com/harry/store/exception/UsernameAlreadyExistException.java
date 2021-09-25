package com.harry.store.exception;

import javax.naming.AuthenticationException;

public class UsernameAlreadyExistException extends AuthenticationException {
    public UsernameAlreadyExistException(String user) {
        super(user);
    }
}
