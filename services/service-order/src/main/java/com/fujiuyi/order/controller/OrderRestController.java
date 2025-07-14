package com.fujiuyi.order.controller;

import com.fujiuyi.order.properties.OrderProperties;
import com.fujiuyi.order.service.OrderService;
import com.order.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//刷新全局参数
@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderRestController {

    @Value("${order.timeout}")
    private String timeout;

    @Value("${order.auto-confirm}")
    private String autoConfirm;

    private final OrderProperties orderProperties;
    private final OrderService orderService;
    public OrderRestController(OrderProperties orderProperties, OrderService orderService) {
        this.orderProperties = orderProperties;
        this.orderService = orderService;
    }

    @GetMapping("/getConfig")
    public String getConfig() {
        return "timeout:" + orderProperties.getTimeout() + " autoConfirm:" + orderProperties.getAutoConfirm();
    }

    /**
     * 创建订单
     */
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }

    @GetMapping("/createProduct")
    public Order createProduct() {
        return orderService.createProduct();
    }
}
