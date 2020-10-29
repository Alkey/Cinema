package com.dev.cinema.model.dto;

import com.dev.cinema.annotations.ValidEmail;
import com.dev.cinema.annotations.ValidPassword;
import lombok.Data;

@Data
@ValidPassword(password = "password",
        repeatPassword = "repeatPassword")
public class UserRegistrationDto {
    @ValidEmail
    private String email;
    private String password;
    private String repeatPassword;
}
