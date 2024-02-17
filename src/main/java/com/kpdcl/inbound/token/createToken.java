//package com.kpdcl.inbound.token;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.Date;
//import java.util.Map;
//
//@Component
//public class createToken {
//
//    @Value("${jwt.expiration}")
//    private Long jwtExpiration;
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    public String generateToken(Map<String, Object> jsonData) {
//        Date currentDate = new Date();
//        Date expirationDate = new Date(currentDate.getTime() + jwtExpiration);
//
//        return Jwts.builder()
//                .setPayload(jsonData) // Set the JSON data as the payload directly
//                .setIssuedAt(currentDate)
//                .setExpiration(expirationDate)
//                .signWith(key(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    private Key key() {
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
//    }
//}


package com.kpdcl.inbound.token;


import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
=======
@Service
>>>>>>> 362428b218da9a90627956b9ac23fd4b9d59a7aa
public class createToken {

    // Method to generate token based on JSON data
    public String generateToken(Map<String, Object> jsonData) {
        // Your token generation logic here
        // Example: You can serialize the JSON data to a string and hash it to generate a token
        String serializedData = serializeData(jsonData);
        String token = hash(serializedData);
        return token;
    }

    // Method to serialize JSON data to a string
    private String serializeData(Map<String, Object> jsonData) {
        // Your serialization logic here
        // Example: Convert JSON data to a JSON string
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
            stringBuilder.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }
        return stringBuilder.toString();
    }

    // Method to hash the serialized data to generate a token
    private String hash(String serializedData) {
        // Your hash function implementation here
        // Example: Use MessageDigest to hash the serialized data
        // This is just a placeholder, replace it with your actual implementation
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(serializedData.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}


