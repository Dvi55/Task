package com.kislun.testtask.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kislun.testtask.model.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static final String USERNAME = "username";
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expire.time}")
    private long expireTime;
    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC512(algorithmKey);
    }

    public String generateJWT(User user) {
        return JWT.create()
                .withClaim(USERNAME, user.getId())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + (expireTime * 1000)))
                .sign(algorithm);
    }

    public String getUser(String token) {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        return JWT.decode(token).getClaim(USERNAME).asString();
    }
}
