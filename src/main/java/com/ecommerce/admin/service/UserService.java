package com.ecommerce.admin.service;

import com.ecommerce.admin.dto.CreateUserDTO;
import com.ecommerce.admin.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    User save(CreateUserDTO userDTO);

}
