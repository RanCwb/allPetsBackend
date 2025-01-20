package com.pets.all_pets.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
public class JwtUtil {

    private static final String SECRET_KEY = "fbbc5555-4bcb-4637-9a08-01caed20774b";

    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(Base64.getEncoder().encode(SECRET_KEY.getBytes()));

    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SIGNING_KEY)
                .compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean validateToken(String token, String email) {
        String subject = extractClaims(token).getSubject();
        return (subject.equals(email) && !isTokenExpired(token));
    }

    public static Integer getUserIdFromToken(String token) {
        return extractClaims(token).get("userId", Integer.class);
    }

    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }


}