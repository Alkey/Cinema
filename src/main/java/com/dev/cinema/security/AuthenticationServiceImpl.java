package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isEmpty()) {
            throw new AuthenticationException("Incorrect email " + email);
        }
        User user = userFromDb.get();
        if (isValid(user.getPassword(), password, user.getSalt())) {
            return user;
        }
        throw new AuthenticationException("Incorrect password");
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isPresent()) {
            throw new AuthenticationException("This email " + email + " is already used");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }

    private boolean isValid(String userPassword, String password, byte[] salt) {
        return userPassword.equals(HashUtil.hashPassword(password, salt));
    }
}
