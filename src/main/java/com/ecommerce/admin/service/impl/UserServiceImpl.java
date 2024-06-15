package com.ecommerce.admin.service.impl;

import com.ecommerce.admin.dao.OrderDao;
import com.ecommerce.admin.dao.RoleDao;
import com.ecommerce.admin.dao.UserDao;
import com.ecommerce.admin.dto.CreateUserDTO;
import com.ecommerce.admin.entity.Order;
import com.ecommerce.admin.entity.Role;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.entity.enums.ERole;
import com.ecommerce.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private OrderDao orderDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName(ERole.ADMIN);
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(ERole.USER);
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User save(CreateUserDTO userDTO) {
        User user=new User();
        Role role = roleDao.findByRoleName(userDTO.getRole());
        System.out.println(role);
        user.setUserName(userDTO.getUserName());
        user.setUserLastName(userDTO.getUserLastName());
        user.setUserFirstName(userDTO.getUserFirstName());
        user.setEmail(userDTO.getEmail());
        user.setUserPassword(bCryptPasswordEncoder.encode(userDTO.getUserPassword()));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);

        return userDao.save(user);
    }
}
