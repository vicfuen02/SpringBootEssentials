package com.springbootessentials.springbootessentials.service.order;

import com.springbootessentials.springbootessentials.controller.order.dto.OrderResDTO;
import com.springbootessentials.springbootessentials.service.order.dto.OrderBDTO;

import java.util.List;

public interface OrderService {

    Long createOrder(OrderBDTO order);

    List<OrderBDTO> getOrders();

    OrderBDTO getOrderById(Long id);

}
