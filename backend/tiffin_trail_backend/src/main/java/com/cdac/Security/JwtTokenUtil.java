package com.cdac.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	 @Value("${jwt.secret}")
	    private String secret;

	    @Value("${jwt.expirationMs}")
	    private int expirationMs;
   
	 // Generate token
	    public String generateToken(UserDetails userDetails, String role) {
	        return Jwts.builder()
	                .setSubject(userDetails.getUsername())
	                .claim("role", role) //  Add role to the token
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	    }


	    // Extract username
	    public String extractUsername(String token) {
	        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	    }
	    public String extractRole(String token) {
	        return Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody()
	                .get("role", String.class);
	    }
	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	    }


	    // Validate token
	    public boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        final Date expiration = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
	        return expiration.before(new Date());
	    }
	}

