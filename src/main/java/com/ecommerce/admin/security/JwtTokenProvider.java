package com.ecommerce.admin.security;




import com.ecommerce.admin.dao.UserDao;
import com.ecommerce.admin.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final UserDao userDao;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiresIn}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        AdminDetails adminDetails = (AdminDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (GrantedAuthority role : adminDetails.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        User adminUser = userDao.findById(adminDetails.getId()).get();

        return Jwts.builder()
                .setId(adminUser.getId() + "")
                .setSubject(adminDetails.getId() + "")
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("authorities", grantedAuthorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .compact();
    }


    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature", ex);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token" + ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token" + ex);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty" + ex);
        }
        return false;
    }
}