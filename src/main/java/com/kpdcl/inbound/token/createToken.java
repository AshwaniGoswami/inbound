//package com.kpdcl.inbound.token;
//
//import java.util.Date;
//import java.util.Map;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//public class createToken {
//    // Replace these with your own secret and expiration time
//    private static final String SECRET_KEY = "your_secret_key";
//    private static final long EXPIRATION_TIME_MS = 3600000; // 1 hour
//
//    public static String generateToken(Map<String, Object> jsonData) {
//        // Create JWT token using the JSON data
//        String token = Jwts.builder()
//                .setClaims(jsonData)
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
//                .compact();
//        return token;
//    }
//}


package com.kpdcl.inbound.token;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class createToken {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private static final long EXPIRATION_TIME_MS = 3600000; // 1 hour

    public String generateToken(Map<String, Object> jsonData) {
        // Create JWT token using the JSON data
        String token = Jwts.builder()
                .setClaims(jsonData)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .compact();
        return token;
    }
}
