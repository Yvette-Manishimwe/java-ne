package com.ecommerce.admin.security;


import com.ecommerce.admin.dao.UserDao;
import com.ecommerce.admin.entity.User;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDao userDao;


    @Transactional
    public UserDetails loadByUserId(Long id) {
        User user = this.userDao.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: "+id));
        return AdminDetails.create(user);
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("user not found with email of "+username));
        return AdminDetails.create(user);
    }
}
