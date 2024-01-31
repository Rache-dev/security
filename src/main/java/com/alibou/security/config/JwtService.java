package com.alibou.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtService {
    //generating the signInKey
    private static final String SECRET_KEY = "2a810ba96742548624e61f5a43f22bfa37f2f362e988991f54c676781edd4182";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    //extracting information from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extactAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return  generateToken(new HashMap<>(), userDetails);
    }

    //a method that will and can validate a token
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //a method that will help us generate a token
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    /** method that takes a JWT token as input,
     * uses the provided signing key to parse and verify the token
     * and the returns the claims contained in the body of the JWT
     */
    private Claims extactAllClaims(String token){
        return Jwts
                .parserBuilder() //start building a parser for the JWT
                .setSigningKey(getSignInKey()) //set the signing key used to verify the signature of the JWT
                .build() //build the JWT parser
                .parseClaimsJws(token) //Parse the JWS(JSON Web Signature)from the token
                .getBody(); //gets the body of the JWT which contains the claims
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
