package com.tangu.tangucore.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fenglei108 on 8/29/17.
 */
@SuppressWarnings("ALL")
@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;

    static final String CLAIM_KEY_ROLE = "role";
    static final String CLAIM_KEY_TENANT = "tenant";
    public static final String AUDIENCE_UNKNOWN = "unknown";
    public static final String AUDIENCE_WEB = "web";
    public static final String AUDIENCE_MOBILE = "mobile";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public JwtUser getJwtUserFromToken(String token){
        JwtUser user = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            Collection<HashMap> coll = (Collection<HashMap>)claims.get(CLAIM_KEY_ROLE);
            List list = coll.stream()
                    .map(map -> new SimpleGrantedAuthority((String)map.get("authority")))
                    .collect(Collectors.toList());
            String tenant = (String)claims.get(CLAIM_KEY_TENANT);
            user = new JwtUser(
                    Long.parseLong(claims.getId()),
                    tenant,
                    claims.getSubject(),
                    null,
                    list,
                    true,
                    null
            );
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    public String generateToken(JwtUser userDetails) {
        return generateToken(userDetails, AUDIENCE_UNKNOWN);
    }

    public String generateToken(JwtUser userDetails, String type) {
        Map<String, Object> claims = new HashMap<>(16);

        claims.put(CLAIM_KEY_ROLE, userDetails.getAuthorities());
        claims.put(CLAIM_KEY_TENANT, userDetails.getTenant());
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setId(""+userDetails.getId())
                .setSubject(userDetails.getUsername())
                .setExpiration(expirationDate)
                .setIssuedAt(createdDate)
                .setAudience(type)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            final Date createdDate = new Date();
            final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expirationDate)
                    .setIssuedAt(createdDate)
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    //TODO implement this
    public Boolean validateToken(String token) {
//        JwtUser user = (JwtUser) userDetails;
//        final String username = getUsernameFromToken(token);
//        final Date created = getCreatedDateFromToken(token);
//        //final Date expiration = getExpirationDateFromToken(token);
//        return (
//                username.equals(user.getUsername())
//                        && !isTokenExpired(token)
//                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
        return true;
    }
}
