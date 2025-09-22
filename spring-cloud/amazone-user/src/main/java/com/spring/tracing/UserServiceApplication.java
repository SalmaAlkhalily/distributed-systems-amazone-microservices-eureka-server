package com.spring.tracing;

import com.spring.tracing.model.User;
import com.spring.tracing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication implements CommandLineRunner{
@Autowired
UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		User user1=new User("salma","alkhalili","salma@gmail.com","123");
		User user2=new User("inas","nasri","inas@gmail.com","456");
		userService.save(user1);
		userService.save(user2);
	}
}
