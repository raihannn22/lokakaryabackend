package com.example.lokakarya.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    private Key jwtKey;

    @PostConstruct
    public void init() {
        jwtKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Date now = new Date();
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpirationMs)).signWith(jwtKey).compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();

        // Asumsikan userDetails memiliki metode getRole() yang mengembalikan String atau List<String>
        // Misalnya, jika role disimpan dalam userDetails.getUsername() atau userDetails.getRoles():
        String userRole = userDetails.getAuthorities().toString(); // Sesuaikan dengan implementasi Anda
        extraClaims.put("role", userRole);

        return generateToken(extraClaims, userDetails);
    }

//    public String generateToken(UserDetails userDetails) {
//        return generateToken(new HashMap<>(), userDetails);
//    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(jwtKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
}


    public boolean isTokenValid(String token) {
        return extractExpirationDate(token).after(new Date());
    }

}
