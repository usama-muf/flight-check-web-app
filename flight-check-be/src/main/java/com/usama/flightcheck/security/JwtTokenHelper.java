package com.usama.flightcheck.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	private final String SECRET_KEY = "secretisyourSecretsecretisyourSecretsecretisyourSecretsecretisyourSecretsecretisyourSecretsecretisyourSecretsecretisyourSecretsecretisyourSecretsecretisyourSecretsecretisyourSecretsecretisyourSecret";

	   public String generateToken(UserDetails userDetails) {
	      Map<String, Object> claims = new HashMap<>();
	      return createToken(claims, userDetails.getUsername()); 
	   }

	   private String createToken(Map<String, Object> claims, String subject) {
	      return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*2))
	            .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	   }

	   public boolean validateToken(String token, UserDetails userDetails) {
	      final String username = extractUsername(token);
	      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	   }

	   private boolean isTokenExpired(String token) {
	      return extractExpiration(token).before(new Date());
	   }

	   public String extractUsername(String token) {
	      return extractClaim(token, Claims::getSubject);
	   }

	   public Date extractExpiration(String token) {
	      return extractClaim(token, Claims::getExpiration);
	   }

	   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	      final Claims claims = extractAllClaims(token);
	      return claimsResolver.apply(claims);
	   }

	   private Claims extractAllClaims(String token) {
	      return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	   }
}
