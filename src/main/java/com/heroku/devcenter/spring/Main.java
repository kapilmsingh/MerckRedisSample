package com.heroku.devcenter.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Main {
	 
    public static void main(String[] args) {
 
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
 
        JedisPool pool = ctx.getBean(JedisPool.class);
        Jedis jedis = pool.getResource();
 
        try {
            String key = "testKey";
            String value = "testValueFromSpring";
            jedis.set(key, value);
            System.out.println("Value set into Redis is: " + value);
            System.out.println("Value retrieved from Redis is: " + jedis.get(key));
        } finally {
            pool.returnResource(jedis);
        }
 
    }
 
}