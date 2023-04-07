package com.redis.application.redisapplication;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonController {
	
	//There are many way we can store the data
		//1)string: string
		//2)string: list 
		//3)string: hash<string:string>
	
	private static final String PERSON_REDIS_STRING_KEY_PREFIX = "per::";
	
	@Autowired
	RedisTemplate<String,Object>redisTemplate;
	
	@PostMapping("/value")
	public void saveValue(@RequestBody Person person,@RequestParam(value="expiry",required=false) Integer expiry ) {
		if(expiry==null) {
		redisTemplate.opsForValue().set(getPersonKey(person.getId()),person);
		}
		
		else {
			redisTemplate.opsForValue().set(getPersonKey(person.getId()), person,expiry, TimeUnit.SECONDS);
		}
	}
	private String getPersonKey(Integer personId) {
		return PERSON_REDIS_STRING_KEY_PREFIX + personId;
	}
	@GetMapping("/value")
	public Person saveValue(Person person) {
		return (Person) redisTemplate.opsForValue().get(person.getId());
	}
	
	
	
	

}
