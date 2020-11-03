package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.RoleName;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService service;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = service.findByEmail(email);
        UserBuilder builder = null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(email);
            builder.password(user.getPassword());
            builder.roles(user.getRoles()
                    .stream()
                    .map(Role::getRoleName)
                    .map(RoleName::name)
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("Can't found user");
        }
        return builder.build();
    }
}
