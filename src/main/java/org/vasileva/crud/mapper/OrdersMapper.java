package org.vasileva.crud.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.vasileva.crud.dto.OrdersDto;
import org.vasileva.crud.entity.Orders;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface OrdersMapper {
    OrdersDto toOrdersDto(Orders order);

    Orders toOrders (OrdersDto ordersDto);

    List<OrdersDto> toListOrdersDto(List<Orders> orders);
}
