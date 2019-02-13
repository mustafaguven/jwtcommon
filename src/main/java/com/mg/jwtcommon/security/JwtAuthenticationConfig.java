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

    @Value("${mg.security.jwt.authLoginUrl:/login}")
    private String authLoginUrl;

    @Value("${mg.security.jwt.zuulLoginUrl:/api/login}")
    private String zuulLoginUrl;

    @Value("${mg.security.jwt.authLoginUrl:/logout}")
    private String authLogoutUrl;

    @Value("${mg.security.jwt.zuulLoginUrl:/api/logout}")
    private String zuulLogoutUrl;

    @Value("${mg.security.jwt.header:Authorization}")
    private String header;

    @Value("${mg.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${mg.security.jwt.expiration:#{30*60}}")
    private int expiration; // default 30 minutes

    @Value("${mg.security.jwt.secret:secretkey}")
    private String secret;
}
