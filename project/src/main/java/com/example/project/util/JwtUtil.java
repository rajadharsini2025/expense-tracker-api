package com.example.project.util;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
  private final String secretKey="mysecretkeymysecretkeymysecretkey";
  public String generateToken(String email)
  {
    return Jwts.builder().subject(email).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSignKey()).compact();
  }
  private Key getSignKey()
  {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }
  public Claims extractAllClaims(String token)
  {
    return Jwts.parser().verifyWith((javax.crypto.SecretKey)getSignKey())
               .build().parseSignedClaims(token).getPayload();
  }
  public boolean validateToken(String token,String email)
  {
    return extractEmail(token).equals(email);
  }
  public String extractEmail(String token)
  {
    return extractAllClaims(token).getSubject();
  }

}
