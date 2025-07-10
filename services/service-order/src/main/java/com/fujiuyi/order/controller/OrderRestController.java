package com.fujiuyi.order.controller;

import com.fujiuyi.order.service.OrderService;
import com.order.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderRestController {


    private final OrderService orderService;
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 创建订单
     */
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }
}
