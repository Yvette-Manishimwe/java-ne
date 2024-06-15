package com.ecommerce.admin.controller;

import com.ecommerce.admin.entity.Order;
import com.ecommerce.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/add")
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @RequestParam Long id) {

        Order savedOrder = orderService.save(order, id);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Add other endpoints as needed
}
