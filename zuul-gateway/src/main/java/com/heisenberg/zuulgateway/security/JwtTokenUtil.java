package com.heisenberg.zuulgateway.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

/**
 * @author heisenberg
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2020605591108951986L;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    private Clock clock = DefaultClock.INSTANCE;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public LocalDate getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(LocalDate created, LocalDate lastPasswordReset) {
        return (lastPasswordReset != null && created.isBefore(lastPasswordReset));
    }

    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("scopes", userDetails.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

//    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
//        final Date created = getIssuedAtDateFromToken(token);
//        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
//                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
//    }
//
//    public String refreshToken(String token) {
//        final Date createdDate = clock.now();
//        final Date expirationDate = calculateExpirationDate(createdDate);
//
//        final Claims claims = getAllClaimsFromToken(token);
//        claims.setIssuedAt(createdDate);
//        claims.setExpiration(expirationDate);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }

//    public Boolean validateToken(String token, UserDetails userDetails) {
//        JwtUser user = (JwtUser) userDetails;
//        final String username = getUsernameFromToken(token);
//        final Date created = getIssuedAtDateFromToken(token);
//        //final Date expiration = getExpirationDateFromToken(token);
//        return (
//                username.equals(user.getUsername())
//                        && !isTokenExpired(token)
//                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
//        );
//    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final LocalDate created = getIssuedAtDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
        );
    }
}
