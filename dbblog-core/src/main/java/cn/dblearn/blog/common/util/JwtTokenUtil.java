package cn.dblearn.blog.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtTokenUtil {
    public JwtTokenUtil() {
    }

    public static String generateToken(Map<String, Object> claims, String secret, Integer expiration) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate(expiration)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private static Date generateExpirationDate(Integer expiration) {
        return new Date(System.currentTimeMillis() + (long)(expiration * 1000));
    }

    public static String getUsernameFromToken(String token, String secret) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token, secret);
            username = claims.getSubject();
        } catch (Exception var4) {
            username = null;
        }

        return username;
    }

    public static Object getObjectFromToken(String token, String secret, String data) {
        Claims claims = getClaimsFromToken(token, secret);
        return claims.get(data);
    }

    private static Claims getClaimsFromToken(String token, String secret) {
        return (Claims)Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public static long checkJwtExpired(String token, String secret) {
        long expired = -1L;

        try {
            Claims claims = getClaimsFromToken(token, secret);
            if (null != claims) {
                expired = (claims.getExpiration().getTime() - (new Date()).getTime()) / 1000L;
            }

            return expired;
        } catch (ExpiredJwtException var5) {
            return expired;
        }
    }
}
