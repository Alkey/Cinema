package com.dev.cinema.validators;

import com.dev.cinema.annotations.ValidEmail;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    public static final String VALID_EMAIL_ADDRESS_REGEX =
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraint) {
        return email != null && email.matches(VALID_EMAIL_ADDRESS_REGEX);
    }
}
