package com.springbootessentials.springbootessentials.service.order.mapper;

import com.springbootessentials.springbootessentials.repository.dto.OrderEntity;
import com.springbootessentials.springbootessentials.service.order.dto.OrderBDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderServiceMapper {

    public abstract OrderBDTO toBDTO(OrderEntity orderEntity);
    public abstract List<OrderBDTO> toOrderListBDTO(List<OrderEntity> orderEntity);
    public abstract OrderEntity toEntity(OrderBDTO orderEntity);

}
