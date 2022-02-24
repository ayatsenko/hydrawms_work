package com.idltd.hydramob.utils.security;

import com.idltd.hydramob.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.idltd.hydramob.utils.security.SecurityConstants.SECRET;
import static com.idltd.hydramob.utils.security.SecurityConstants.TOKEN_PREFIX;

public class Jwt_token_util {

    public static final String createJWT(User user, String organization) {

        String token = Jwts.builder()
                .setSubject(user.getUsername())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

//        Map<String, Object> tokenData = new HashMap<>();
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.YEAR, 100);
//        tokenData.put("user",user.getUsername());
//        tokenData.put("organization", organization);
//        String result = Jwts.builder()
//                .setClaims(tokenData)
//                .setIssuedAt(new Date())
//                .setExpiration(calendar.getTime())
//                .setIssuer(ISSUER)
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
        return token;
    }
    public static String parseJWT(String token) {
        String user = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        return user;
    }
}
