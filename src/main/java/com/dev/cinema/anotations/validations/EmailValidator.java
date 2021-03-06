package com.dev.cinema.anotations.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
        ConstraintValidator<EmailConstraint, String> {
    private static final String EMAIL_REGEX = "^[\\w-+]+(\\.[\\w]+)*@([\\w-+]+(\\.[\\w]+)*)";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
