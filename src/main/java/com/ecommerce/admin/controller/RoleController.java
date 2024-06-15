package com.ecommerce.admin.controller;


import com.ecommerce.admin.entity.Role;
import com.ecommerce.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @PostMapping("/create")
    public Role createNewRole(@RequestBody Role role) {
        System.out.println("========= Hello ======");
       return roleService.createNewRole(role);
    }
    @GetMapping
    public String greetings(){
        return "Hello user";
    }



}

