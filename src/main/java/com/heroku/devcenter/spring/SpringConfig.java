package com.heroku.devcenter.spring;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Configuration
public class SpringConfig {
 
	@Bean
	public JedisPool getJedisPool(){
		
		try{
			
			URI redisURI = new URI(System.getenv("REDISTOGO_URL"));
			return new JedisPool(new JedisPoolConfig(),
					redisURI.getHost(), 
					redisURI.getPort(), 
					Protocol.DEFAULT_TIMEOUT, 
					redisURI.getUserInfo().split(":",2)[1]);
			
		}catch(Exception e){
			throw new RuntimeException("Redis coud not be configured fron given URL from env var");
		}
	}
	
}
