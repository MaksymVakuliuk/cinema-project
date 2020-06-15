package com.dev.cinema.model.dto.user;

import com.dev.cinema.anotations.validations.EmailConstraint;
import com.dev.cinema.anotations.validations.MatchingPasswordsConstraint;
import javax.validation.constraints.NotNull;

@MatchingPasswordsConstraint.List({
        @MatchingPasswordsConstraint(
                password = "password",
                repeatPassword = "repeatPassword"
        )
})
public class UserRequestDto {
    @NotNull
    @EmailConstraint
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
