package com.dev.cinema.model.dto.order;

import com.dev.cinema.model.dto.ticket.TicketResponseDto;
import com.dev.cinema.model.dto.user.UserResponseDto;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long orderId;
    private List<TicketResponseDto> ticketResponseDtoList;
    private LocalDateTime orderDate;
    private UserResponseDto userResponseDto;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
