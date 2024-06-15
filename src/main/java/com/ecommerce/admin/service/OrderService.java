package com.ecommerce.admin.service;

import com.ecommerce.admin.entity.Order;

public interface OrderService {
    Order save(Order order, Long id);
    // Add other methods if needed
}
