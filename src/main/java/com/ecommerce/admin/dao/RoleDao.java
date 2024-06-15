package com.ecommerce.admin.dao;

import com.ecommerce.admin.entity.Role;
import com.ecommerce.admin.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    /**
     * @Todo reivise java persistence query language ( JPQL )
     * @param name
     * @return
     */
    public boolean existsByRoleName(ERole name);

    public Role findByRoleName(ERole roleName);


}
