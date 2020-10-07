package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new AuthenticationException("Incorrect email " + email);
        }
        if (isValid(user.getPassword(), password, user.getSalt())) {
            return user;
        }
        throw new AuthenticationException("Incorrect password");
    }

    @Override
    public User register(String email, String password) {
        User user = userService.findByEmail(email);
        if (user != null) {
            throw new AuthenticationException("This email " + email + " is already used");
        }
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }

    private boolean isValid(String userPassword, String password, byte[] salt) {
        return userPassword.equals(HashUtil.hashPassword(password, salt));
    }
}
