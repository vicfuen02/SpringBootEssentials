package com.springbootessentials.springbootessentials.service.order.impl;

import com.springbootessentials.springbootessentials.common.exception.SPEssentialsExceptionFactory;
import com.springbootessentials.springbootessentials.repository.OrderJpaRepository;
import com.springbootessentials.springbootessentials.repository.entity.OrderEntity;
import com.springbootessentials.springbootessentials.service.order.OrderServiceCommand;
import com.springbootessentials.springbootessentials.service.order.dto.OrderBDTO;
import com.springbootessentials.springbootessentials.service.order.exceptions.InvalidOrderIdSPEssentialsException;
import com.springbootessentials.springbootessentials.service.order.exceptions.OrderExceptionsEnum;
import com.springbootessentials.springbootessentials.service.order.mapper.OrderServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceCommandImpl implements OrderServiceCommand {


//    private OrderRepositoryMock orderRepository;
    private OrderJpaRepository orderJpaRepository;
    private OrderServiceMapper orderServiceMapper;

    @Autowired
    public OrderServiceCommandImpl(OrderJpaRepository orderJpaRepository, OrderServiceMapper orderServiceMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderServiceMapper = orderServiceMapper;
    }


    @Override
    public OrderBDTO getOrderById(Long id) {

        if (id <= 0) {
            throw new InvalidOrderIdSPEssentialsException();
        }

        OrderEntity orderEntity = this.orderJpaRepository.findById(id)
                .orElseThrow(() -> SPEssentialsExceptionFactory.throwException(OrderExceptionsEnum.ORDER_NOT_FOUND));

        return this.orderServiceMapper.toBDTO(orderEntity);
    }

    public Long updateOrder(OrderBDTO order) {
        OrderEntity orderEntity = this.orderServiceMapper.toEntity(order);
        OrderEntity orderUpdated = this.orderJpaRepository.save(orderEntity);
        return orderUpdated.getId();
    }

}
