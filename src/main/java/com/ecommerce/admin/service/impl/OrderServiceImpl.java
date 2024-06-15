package com.ecommerce.admin.service.impl;

import com.ecommerce.admin.dao.OrderDao;
import com.ecommerce.admin.dao.UserDao;
import com.ecommerce.admin.entity.Order;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.entity.enums.OrderStatus;
import com.ecommerce.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Order save(Order order, Long id) {
       Optional<User> userOptional=userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Set user to the order
            order.setUser(user);

            // Set other fields of the order
            order.setAmount(0L);
            order.setTotalAmount(0L);
            order.setDiscount(0L);
            order.setOrderStatus(OrderStatus.Pending);

            // Save the order
            return orderDao.save(order);
        } else {
            // Handle case where user is not found
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }

    // Implement other methods if needed
}
