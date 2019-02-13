package com.mg.jwtcommon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    private static final String LOCALHOST = "localhost";
    private static final String ENV_REDIS_HOSTNAME = "ENV_REDIS_HOSTNAME";
    private static final String BEARER = "Bearer ";
    private static final String EMPTY_STRING = "";
    private static final String FORBIDDEN = "forbidden";
    private static final String EXCEPTION_MSG_FORBIDDEN = "forbidden token, do login again";

    @Bean
    private JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        try {
            redisConf.setHostName(System.getenv(ENV_REDIS_HOSTNAME));
        } catch (Exception ex){
            redisConf.setHostName(LOCALHOST);
        }
        return new JedisConnectionFactory(redisConf);
    }

    @Bean
    private RedisTemplate<String, String> redisTemplate() {

        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    public boolean forgetToken(String token) {
        try {
            token = token.replace(BEARER, EMPTY_STRING);
            redisTemplate().opsForValue().set(token, FORBIDDEN);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void isForbiddenToken(String token) {
        if (redisTemplate().opsForValue().get(token) != null) {
            throw new RuntimeException(EXCEPTION_MSG_FORBIDDEN);
        }
    }
}
