package com.journaldev.bootifulmongodb.interceptor;


//import com.journaldev.bootifulmongodb.utils.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;



@Service
public class RateLimiter {

    private Jedis jedis = new Jedis("localhost");

//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
//
//    protected boolean allowWithRedisTemplate(int maxRequest, String key, Long TIME_WINDOW_IN_SECONDS) {
//        Long nowSeconds = System.currentTimeMillis() / 1000;
//        System.out.println("now seconds:: "+ nowSeconds);
//        Long removed = redisTemplate.opsForZSet().removeRangeByScore(key, 0, nowSeconds-TIME_WINDOW_IN_SECONDS);
//        System.out.println("removed:: "+ removed + " key:: "+ key);
//        Long count = redisTemplate.opsForZSet().count(key, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
//
//        System.out.println("count:: "+count+ " :: allow request :: "+ maxRequest);
//
//        boolean allowed = count < maxRequest;
//        System.out.println(" allowed:: "+ allowed);
//        if (allowed) {
//            redisTemplate.opsForZSet().add(key, nowSeconds,  System.currentTimeMillis());
//        }
//        return allowed;
//    }

    protected boolean allow(int maxRequest, String key, Long TIME_WINDOW_IN_SECONDS) {
        Long nowSeconds = System.currentTimeMillis() / 1000;
        jedis.zremrangeByScore(key, 0, nowSeconds-TIME_WINDOW_IN_SECONDS);
        Long count = jedis.zcount(key, "-inf", "+inf");
        boolean allowed = count < maxRequest;
        if (allowed) {
            jedis.zadd(key, nowSeconds, "" + System.currentTimeMillis());
        }
        return allowed;
    }
}
