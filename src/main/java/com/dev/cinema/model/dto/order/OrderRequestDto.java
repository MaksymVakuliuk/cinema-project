package com.dev.cinema.model.dto.order;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class OrderRequestDto {
    @NotNull
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
