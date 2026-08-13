package org.accountBook.service.Impl;

import org.accountBook.dto.LoginRequest;
import org.accountBook.mapper.LoginMapper;
import org.accountBook.model.User;
import org.accountBook.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
    @Override
    public User loginToken(LoginRequest login){

        User user=loginMapper.login(login);
        return user;


    }

}
