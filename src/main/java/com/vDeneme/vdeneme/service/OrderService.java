package com.vDeneme.vdeneme.service;

import com.vDeneme.vdeneme.model.Order;
import com.vDeneme.vdeneme.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrderByGsmNumber(String gsmNumber){
        return orderRepository.findByGsmNumber(gsmNumber);
    }

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

}
