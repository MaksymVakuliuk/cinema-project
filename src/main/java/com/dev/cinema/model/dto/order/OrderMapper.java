package com.dev.cinema.model.dto.order;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.ticket.TicketMapper;
import com.dev.cinema.model.dto.user.UserMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;
    private final UserMapper userMapper;

    public OrderMapper(TicketMapper ticketMapper, UserMapper userMapper) {
        this.ticketMapper = ticketMapper;
        this.userMapper = userMapper;
    }

    public OrderResponseDto convertToResponseDto(Order order) {
        var orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setTicketResponseDtoList(order.getTickets()
                .stream()
                .map(ticketMapper::convertToResponseDto)
                .collect(Collectors.toList()));
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setUserResponseDto(userMapper.convertToResponseDto(order.getUser()));
        return orderResponseDto;
    }
}
