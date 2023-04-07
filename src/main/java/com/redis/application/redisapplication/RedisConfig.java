package com.redis.application.redisapplication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Bean
    public LettuceConnectionFactory getFactory() {
		//Here I have to provide the end point along with password
		RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration("redis-18068.c305.ap-south-1-1.ec2.cloud.redislabs.com",18068);
		redisStandaloneConfiguration.setPassword("AEtIxCbvMj9xer1FgGkxLuAZAau0JD6k");
		LettuceConnectionFactory lettuceConnectionFactory=new LettuceConnectionFactory(redisStandaloneConfiguration);	
		return lettuceConnectionFactory;
	}
	@Bean
	
	public RedisTemplate<String, Object> getTemplate(){
		RedisTemplate<String, Object>redisTemplate=new RedisTemplate<String, Object>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(getFactory());
		return redisTemplate;	
	}
}
