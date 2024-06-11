package com.uep.wap.mappers;
import com.uep.wap.dto.BookDto;
import com.uep.wap.dto.OrderDto;
import com.uep.wap.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderDto orderToOrderDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        // Assuming OrderDto has a setter method for setting BookDto list
        orderDto.setBooks(order.getBookList().stream()
                .map(book -> new BookDto(/* pass book properties here */))
                .collect(Collectors.toList()));

        orderDto.setId(order.getId());
//        orderDto.setCustomer(/* map Customer here */);
        orderDto.setCreationDate(order.getCreationDate());
        orderDto.setCompletionDate(order.getCompletionDate());
        orderDto.setTotalCost(order.getTotalCost());
        orderDto.setStatus(order.getStatus().toString());

        return orderDto;
    }

    @Override
    public Order orderDtoToOrder(OrderDto orderDto) {
        // Implement the conversion logic from OrderDto to Order
        // This method is required by the OrderMapper interface
        return null; // Placeholder return statement, replace with actual implementation
    }

    @Override
    public List<OrderDto> orderListToOrderDtoList(List<Order> orders) {
        if (orders == null) {
            return null;
        }

        return orders.stream()
                .map(this::orderToOrderDto)
                .collect(Collectors.toList());
    }
}
