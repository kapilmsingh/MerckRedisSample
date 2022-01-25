package com.heroku.devcenter;

import java.net.URI;

import org.springframework.context.ApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class Main {

	public static void main(String[] args){
		
		
		JedisPool pool;
		try{
			
			URI redisURI = new URI(System.getenv("REDISTOGO_URL"));
			pool = new JedisPool(new JedisPoolConfig(),
					redisURI.getHost(), 
					redisURI.getPort(), 
					Protocol.DEFAULT_TIMEOUT, 
					redisURI.getUserInfo().split(":",2)[1]);
			
		}catch(Exception e){
			throw new RuntimeException("Redis coud not be configured fron given URL from env var");
		}
		
		Jedis jedis = pool.getResource();
		
		try {			
			String key = "testKey";
			String value = "testValue";
			jedis.set(key, value);
			System.out.println("Value set into redis is : "+value);
			System.out.println("Value retrieved from redis is : "+jedis.get(key));
		} finally {
				pool.returnResource(jedis);
		}
		
	}
}
