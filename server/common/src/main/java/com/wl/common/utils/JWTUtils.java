package com.wl.common.utils;
import io.jsonwebtoken.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-07-24 15:06
 **/
public class JWTUtils {
    private final static String  SECRET = "This will be a great Bo in the future";
    public static  String getJwtToken(String userId){
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        //有10天有效期
        nowTime.add(Calendar.DATE, 10);
        Date expiresDate = nowTime.getTime();
        Claims claims = Jwts.claims();
        claims.put("userId",userId);
        String token = Jwts.builder().setClaims(claims).setExpiration(expiresDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }
    public static Claims parseJwtToken(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        String signature = jws.getSignature();
        Map<String, String> header = jws.getHeader();
        Claims claims = jws.getBody();
        return claims;
    }

}
