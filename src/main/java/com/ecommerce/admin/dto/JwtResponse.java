package com.ecommerce.admin.dto;


import com.ecommerce.admin.entity.User;
import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private User user;
}
