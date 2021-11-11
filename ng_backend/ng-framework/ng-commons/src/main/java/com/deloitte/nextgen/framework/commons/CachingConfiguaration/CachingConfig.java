package com.deloitte.nextgen.framework.commons.CachingConfiguaration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
@Slf4j
public class CachingConfig {
    @Bean
    JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory rf = new JedisConnectionFactory();
        rf.setUsePool(true);
        return rf;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> rt = new RedisTemplate<>();
        rt.setConnectionFactory(redisConnectionFactory());

        return rt;
    }

    @Bean
    @Primary
    public CacheManager initRedisCacheManager(RedisConnectionFactory factory) {
        log.info("-----Inside initRedisCacheManager Method----------");
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder.fromConnectionFactory(factory);
        return builder.build();
    }

//    @Bean
//    @Primary
//    public CacheManager primaryCacheManager()
//    {
//        return new RedisCacheManager(redisTemplate());
//    }

    @Bean
    //@Primary
    public CacheManager secondaryCacheManger() {
        log.info("-----Inside secondaryCacheManger Method----------");
        return new ConcurrentMapCacheManager();
    }
}