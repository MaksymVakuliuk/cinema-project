package com.dev.cinema.model.dto.user;

import com.dev.cinema.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto convertToResponseDto(User user) {
        var userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
