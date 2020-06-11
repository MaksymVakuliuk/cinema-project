package com.dev.cinema.model.dto.order;

import org.springframework.stereotype.Component;

@Component
public class OrderRequestDto {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
