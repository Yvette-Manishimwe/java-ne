package com.ecommerce.admin;

import com.ecommerce.admin.dao.RoleDao;
import com.ecommerce.admin.entity.Role;
import com.ecommerce.admin.entity.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor

public class AdminApplication  {
    public final RoleDao roleDao;
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
    @Bean
    public int initializeRoles(){
        List<ERole> roles = new ArrayList<>();
        roles.add(ERole.ADMIN);
        roles.add(ERole.USER);
//        roles.add(ERole.TRADER);

        for(ERole roleName :roles) {
            if (!roleDao.existsByRoleName(roleName)) {
                Role role = new Role();
//                role.setId();
                role.setRoleDescription(roleName.toString());
                role.setRoleName(roleName);
                roleDao.save(role);
            }
        }
        return 0;
    }

}



