package com.dev.cinema.model.dto.shoppingcart;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCartRequestDto {
    @NotNull
    private List<Long> ticketsId;
    @NotNull
    private LocalDateTime localDateTime;
    @NotNull
    private Long userId;

    public List<Long> getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(List<Long> ticketsId) {
        this.ticketsId = ticketsId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
