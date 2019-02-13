package com.mg.jwtcommon.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Only one property 'mg.security.jwt.secret' is mandatory.
 */
@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${mg.security.jwt.url:/api/login}")
    private String url;

    @Value("${mg.security.jwt.header:Authorization}")
    private String header;

    @Value("${mg.security.jwt.prefix:Bearer}")
    private String prefix;

    //@Value("${mg.security.jwt.expiration:#{24*60*60}}")
    @Value("${mg.security.jwt.expiration:#{60}}")
    private int expiration; // default 1 minute

    @Value("${mg.security.jwt.secret}")
    private String secret;
}
