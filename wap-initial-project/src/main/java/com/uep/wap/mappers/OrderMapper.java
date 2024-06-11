package com.uep.wap.mappers;

import com.uep.wap.dto.OrderDto;
import com.uep.wap.model.Order;
import java.util.List;

public interface OrderMapper {

    OrderDto orderToOrderDto(Order order);

    Order orderDtoToOrder(OrderDto order);
    List<OrderDto> orderListToOrderDtoList(List<Order> orders);
}
