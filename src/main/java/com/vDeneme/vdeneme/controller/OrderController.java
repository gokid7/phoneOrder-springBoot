package com.vDeneme.vdeneme.controller;

import com.vDeneme.vdeneme.errorHandling.ResourceNotFoundException;
import com.vDeneme.vdeneme.model.Order;
import com.vDeneme.vdeneme.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{gsmNumber}")
    private List<Order> listingOrders(@PathVariable("gsmNumber") String gsmNumber)  {
        return orderService.getOrderByGsmNumber(gsmNumber);
    }
    
    @PostMapping(path = "/create")
    private Order createOrder(@RequestBody Order order){

        if(order.getStatus() != 1){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Status sadece 1 olabilir.");
        }

        List<Order> orderList = orderService.getOrderByGsmNumber(order.getGsmNumber());
        if(orderList.isEmpty()) {
            return  orderService.createOrder(order);
        }

        orderList.forEach(order1 -> {
            if(order1.getStatus() == 1 && order.getStatus() == 1){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Aktif sipariş vardır.");
            }
        });
        return orderService.createOrder(order);
    }

    @PutMapping(path = "/deActive/{gsmNumber}")
    private Order deActivatingOrder(@PathVariable("gsmNumber") String gsmNumber) throws ResourceNotFoundException {
        List<Order> orderList = orderService.getOrderByGsmNumber(gsmNumber);
        if(orderList.isEmpty()) {
            throw new ResourceNotFoundException(String.join(" ",String.valueOf(gsmNumber), "Gsm numarası ile ilişkili sipariş bulunamadı"));
        }
        Optional<Order> activeOrder = orderList.stream().filter(order -> order.getStatus() == 1).findFirst();
        activeOrder.get().setStatus(0);
        orderService.createOrder(activeOrder.get());

        return activeOrder.get();
    }

}
