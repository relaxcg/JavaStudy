package org.relaxcg.redis.config.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author relaxcg
 * @date 2023/11/29 15:48
 */
@Slf4j
@Component
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    public static void set(String k, Object v) {
        redisTemplate.opsForValue().set(k, v);
    }

    public static Object get(String k) {
        return redisTemplate.opsForValue().get(k);
    }
}
