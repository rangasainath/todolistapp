package com.Application.todolistapp.Util;

import com.Application.todolistapp.Entity.UserAuthEntity;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Component
public class JWTUtility {

    private static final String SECRET_KEY = "your-secure-secret-key-min-32bytes";
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
   public JWTUtility(){

   }

    public String generateToken(String username, long expiryMinutes, UserAuthEntity userauth){
        HashMap<String, Object> TokenObject = new HashMap<String, Object>();
        TokenObject.put("userId",userauth.getId());
        return  Jwts.builder()
                .setClaims(TokenObject)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiryMinutes*60*1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateAndExtractUsername(String token) {
       try{
       return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    catch(JwtException e)
    {
        return null;
    }
    }



}
