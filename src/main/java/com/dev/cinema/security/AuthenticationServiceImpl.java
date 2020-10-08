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
        if (userFromDb.isPresent()
                && isValid(userFromDb.get().getPassword(), password, userFromDb.get().getSalt())) {
            return userFromDb.get();
        }
        throw new AuthenticationException("Incorrect email: " + email + " or password");
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }

    private boolean isValid(String userPassword, String password, byte[] salt) {
        return userPassword.equals(HashUtil.hashPassword(password, salt));
    }
}
