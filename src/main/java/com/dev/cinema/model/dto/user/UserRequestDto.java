package com.dev.cinema.model.dto.user;

import com.dev.cinema.anotations.validations.EmailConstraint;

import javax.validation.constraints.NotNull;

public class UserRequestDto {
    @NotNull
    @EmailConstraint
    private String email;
    @NotNull
    private String password;

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
}
