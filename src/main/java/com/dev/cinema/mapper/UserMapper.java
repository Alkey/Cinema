package com.dev.cinema.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserRequestDto getDtoFromUser(User user) {
        UserRequestDto dto = new UserRequestDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
