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
    public static  String getJwtToken(String key,Object value){

        Calendar nowTime = Calendar.getInstance();
        //有10分钟有效期
        nowTime.add(Calendar.MONTH, 10);
        Date expiresDate = nowTime.getTime();
        Claims claims = Jwts.claims();
        claims.put(key,value);
        return getJwtToken(claims,expiresDate);
    }
    public static  String getJwtToken(String key,Object value,Date endDate){
        Claims claims=Jwts.claims();
        claims.put(key,value);
        return getJwtToken(claims,endDate);
    }
    private  static  String getJwtToken(Claims claims,Date date){
        String token = Jwts.builder().setClaims(claims).setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }
    public static Claims parseJwtToken(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims claims = jws.getBody();
        return claims;
    }
    public static Claims parseJwtTokenAddTime(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims claims = jws.getBody();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,1);//每次访问都会续期1分钟
        claims.setIssuedAt(calendar.getTime());
        return claims;
    }
}
