package org.accountBook.mapper;

import org.accountBook.dto.LoginRequest;
import org.accountBook.model.User;

public interface LoginMapper {

    public User login(LoginRequest loginRequest) ;
}
