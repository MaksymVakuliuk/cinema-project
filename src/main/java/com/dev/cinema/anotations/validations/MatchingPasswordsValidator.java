package com.dev.cinema.anotations.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class MatchingPasswordsValidator
        implements ConstraintValidator<MatchingPasswordsConstraint, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(MatchingPasswordsConstraint constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object repeatPasswordValue = new BeanWrapperImpl(value).getPropertyValue(repeatPassword);

        if (passwordValue != null) {
            return passwordValue.equals(repeatPasswordValue);
        } else {
            return repeatPasswordValue == null;
        }
    }
}
