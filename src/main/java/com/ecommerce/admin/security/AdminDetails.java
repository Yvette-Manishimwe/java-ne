package com.ecommerce.admin.security;


import com.ecommerce.admin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetails implements UserDetails {
    private Long id;

    private String userName;
    private String userFirstName;
    private String userLastName;

    private String email;
    private String userPassword;



    private Collection<? extends GrantedAuthority> authorities;

    public static AdminDetails create(User user) {
        List<GrantedAuthority> authorities =user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString())).collect(Collectors.toList());

        return new AdminDetails(
                user.getId(),
                user.getUserName(),
                user.getUserFirstName(),
                user.getUserLastName(),
                user.getEmail(),
                user.getUserPassword(),
                authorities
                );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
