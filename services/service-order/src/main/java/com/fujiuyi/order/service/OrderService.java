package com.fujiuyi.order.service;

import com.order.Order;

public interface OrderService {

    Order createOrder(Long product, Long userId);

    Order createProduct();
}
