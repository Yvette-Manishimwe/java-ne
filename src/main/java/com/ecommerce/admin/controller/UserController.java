package com.ecommerce.admin.controller;

import com.ecommerce.admin.dto.CreateUserDTO;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/registerNewUser", consumes = {"application/json"})
    public User registerNewUser(@RequestBody CreateUserDTO user) {
        return userService.save(user);
    }

}
