package com.dev.cinema.anotations.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MatchingPasswordsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchingPasswordsConstraint {
    String message() default "Passwords mismatch.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String password();

    String repeatPassword();

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        MatchingPasswordsConstraint[] value();
    }
}
