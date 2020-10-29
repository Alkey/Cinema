package com.dev.cinema.validators;

import com.dev.cinema.annotations.ValidPassword;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<ValidPassword, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object dtoPassword = new BeanWrapperImpl(value)
                .getPropertyValue(password);
        Object dtoRepeatPassword = new BeanWrapperImpl(value)
                .getPropertyValue(repeatPassword);
        return dtoPassword != null && dtoPassword.equals(dtoRepeatPassword);
    }
}
