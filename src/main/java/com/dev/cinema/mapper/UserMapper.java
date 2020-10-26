package com.dev.cinema.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto getResponseDtoFromUser(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
