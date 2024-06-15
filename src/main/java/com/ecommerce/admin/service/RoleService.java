package com.ecommerce.admin.service;

import com.ecommerce.admin.dao.RoleDao;
import com.ecommerce.admin.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    public Role createNewRole(Role role){
         return roleDao.save(role);
    }
}
