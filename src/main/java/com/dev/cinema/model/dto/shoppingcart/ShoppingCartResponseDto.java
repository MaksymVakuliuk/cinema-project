package com.dev.cinema.model.dto.shoppingcart;

import com.dev.cinema.model.dto.ticket.TicketResponseDto;
import com.dev.cinema.model.dto.user.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCartResponseDto {
    private Long shoppingCartId;
    private List<TicketResponseDto> ticketResponseDtoList;
    private LocalDateTime orderDate;
    private UserResponseDto userResponseDto;

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public List<TicketResponseDto> getTicketResponseDtoList() {
        return ticketResponseDtoList;
    }

    public void setTicketResponseDtoList(List<TicketResponseDto> ticketResponseDtoList) {
        this.ticketResponseDtoList = ticketResponseDtoList;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }
}
