package com.dev.cinema.anotations.validations;

import com.dev.cinema.model.dto.user.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class MatchingPasswordsValidator
        implements ConstraintValidator<MatchingPasswordsConstraint, UserRequestDto> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(MatchingPasswordsConstraint constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(userRequestDto).getPropertyValue(password);
        Object repeatPasswordValue = new BeanWrapperImpl(userRequestDto)
                .getPropertyValue(repeatPassword);

        return passwordValue == null || passwordValue.equals(repeatPasswordValue);
    }

}
