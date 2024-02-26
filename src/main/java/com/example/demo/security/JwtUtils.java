package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey("ASaskdjhakdhkahdksadhasdasd6a5ds45sad5ad45as4da4d231as5d46sasddasaa54d6as5d4a65d4a65sd4a65d46a5d456ad46ad4a65d456").parseClaimsJws(authToken);
			return true;			
		}catch (Exception e) {
			LOG.error("ERRORRRRR", e);
		}
		return false;	
	}
	
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey("ASaskdjhakdhkahdksadhasdasd6a5ds45sad5ad45as4da4d231as5d46sasddasaa54d6as5d4a65d4a65sd4a65d46a5d456ad46ad4a65d456").parseClaimsJws(token).getBody().getSubject();
	}
}