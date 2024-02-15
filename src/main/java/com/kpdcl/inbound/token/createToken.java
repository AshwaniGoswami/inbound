
//package com.kpdcl.inbound.token;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//
//
//import java.util.Date;
//import java.util.Map;
//
//@Component
//public class createToken {
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;
//
//    private static final long EXPIRATION_TIME_MS = 3600000; // 1 hour
//
//    public String generateToken(Map<String, Object> jsonData) {
//        
//		// Create JWT token using the JSON data
//        String token = Jwts.builder()
//                .setClaims(jsonData)
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
//                .compact();
//        return token;
//    }
//}

package com.kpdcl.inbound.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class createToken {

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String jsonData) {
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setPayload(jsonData) // Set the JSON data as the payload directly
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}

