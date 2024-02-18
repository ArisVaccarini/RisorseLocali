package com.unicam.risorseLocali.Security.Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

/**
 * Questa classe ha il compito di generare
 * e validare i token per l'autenticazione.
 */
@Component
public class JwtTokenCreator {

    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expiration-time}")
    private long EXPIRATION_TIME;

    private final Logger logger = LoggerFactory.getLogger(JwtTokenCreator.class);

    public Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public String generate(String username, String ruolo) {
        return Jwts.builder()
                .claim("username", username)
                .claim("ruolo", ruolo)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key())
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String extractId(String token) {
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token)
                .getPayload().get("id").toString();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token)
                .getPayload().get("username").toString();
    }

}