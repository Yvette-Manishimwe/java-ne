package com.ecommerce.admin.service.impl;

import com.ecommerce.admin.dao.CartItemsDao;
import com.ecommerce.admin.dao.OrderDao;
import com.ecommerce.admin.dao.ProductDao;
import com.ecommerce.admin.dao.UserDao;
import com.ecommerce.admin.dto.AddProductInCartDto;
import com.ecommerce.admin.entity.CartItems;
import com.ecommerce.admin.entity.Order;
import com.ecommerce.admin.entity.Product;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.entity.enums.OrderStatus;
import com.ecommerce.admin.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartItemsDao cartItemsDao;
    @Autowired
    private ProductDao productDao;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderDao.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems= cartItemsDao.findByProductIdAndOrderIdAndUserId(
                addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());
        if (optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else {
            Optional<Product> optionalProduct= productDao.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userDao.findById(addProductInCartDto.getUserId());

            if(optionalProduct.isPresent() && optionalUser.isPresent()){
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsDao.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount()+ cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);
                orderDao.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);

            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }
}
