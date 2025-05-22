package com.telco.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationMs}")
    private long expirationMs;

    // Clave secreta convertida a formato Key para firmar/verificar JWT
    private Key signingKey;

    @PostConstruct
    private void init() {
        // Convertir la cadena secreta en un Key seguro para HS512
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Genera un JWT con el subject (username o email) y la fecha de expiración.
     *
     * @param subject el valor que se colocará en el campo "sub" (usualmente username/email)
     * @return el token JWT firmado
     */
    public String generateToken(String subject) {
        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plusMillis(expirationMs));

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extrae el username (subject) almacenado en el token JWT.
     *
     * @param token el JWT a parsear
     * @return el subject (username/email) si el token es válido; null si no lo es
     */
    public String extractUsername(String token) {
        try {
            Claims claims = parseClaims(token);
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obtiene la fecha de expiración a partir del token JWT.
     *
     * @param token el JWT firmado
     * @return Instant de expiración si el token es válido; null si no lo es
     */
    public Instant getExpirationDateFromToken(String token) {
        try {
            Claims claims = parseClaims(token);
            return claims.getExpiration().toInstant();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Valida que el token JWT tenga firma válida y no esté expirado.
     *
     * @param token el JWT a validar
     * @return true si es válido; false en caso de expirado o firma inválida
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Parsea las reclamaciones (claims) del token.
     * Lanza excepción si la firma no coincide o el formato es inválido.
     *
     * @param token el JWT a parsear
     * @return Claims extraídos
     */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
