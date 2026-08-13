package org.accountBook.service;

import org.accountBook.dto.LoginRequest;
import org.accountBook.model.User;

public interface LoginService {

    User loginToken(LoginRequest login);
}
