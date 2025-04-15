package com.springbootessentials.springbootessentials.service.order.mapper;

import com.springbootessentials.springbootessentials.repository.entity.OrderEntity;
import com.springbootessentials.springbootessentials.service.common.dto.PageBDTO;
import com.springbootessentials.springbootessentials.service.order.dto.OrderBDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderServiceMapper {

    @Mapping(target = "address.orders", ignore = true)
    public abstract OrderBDTO toBDTO(OrderEntity orderEntity);

    @Named("toBDTOWithoutAddress")
    @Mapping(target = "address", ignore = true)
    public abstract OrderBDTO toBDTOWithoutAddress(OrderEntity orderEntity);
    public abstract List<OrderBDTO> toOrderListBDTO(List<OrderEntity> orderEntity);
    public PageBDTO<OrderBDTO> toOrderPageBDTO(Page<OrderEntity> orderEntity) {
        return new PageBDTO<>(
                this.toOrderListBDTO(orderEntity.getContent()),
                orderEntity.getTotalElements(),
                orderEntity.getTotalPages()
        );
    };

    public abstract OrderEntity toEntity(OrderBDTO orderEntity);

}
