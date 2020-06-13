package com.dev.cinema.model.dto.shoppingcart;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.ticket.TicketMapper;
import com.dev.cinema.model.dto.user.UserMapper;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(UserMapper userMapper, TicketMapper ticketMapper) {
        this.userMapper = userMapper;
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto convertToResponseDto(ShoppingCart shoppingCart) {
        var shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setShoppingCartId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketResponseDtoList(shoppingCart.getTickets()
                .stream()
                .map(ticketMapper::convertToResponseDto)
                .collect(Collectors.toList()));
        shoppingCartResponseDto.setUserResponseDto(userMapper
                .convertToResponseDto(shoppingCart.getUser()));
        shoppingCartResponseDto.setOrderDate(shoppingCart.getOrderDate());
        return shoppingCartResponseDto;
    }
}
