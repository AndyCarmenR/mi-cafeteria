package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

   @Value("${jwt.secret}")
    private String secret;

    private  Key secretKey;
    private final Integer expiration=86400000;// 1 día

    @PostConstruct
    public void init(){
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("La propiedad jwt.secret no está definida");
        }

        byte[] keyBytes = Base64.getDecoder().decode(secret);
        if (keyBytes.length < 32) {
            throw new IllegalStateException("La clave secreta JWT debe tener al menos 256 bits (32 bytes)");
        }
        this.secretKey=Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generarToken(UsuarioPWD user) {
        Map<String, Object> claims = new HashMap<>();
        if (user.getId() != null) {
            claims.put("userId", user.getId()); // Add userId to claims
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getNickname())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraerUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


}
