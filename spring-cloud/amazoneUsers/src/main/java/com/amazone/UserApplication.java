package com.amazone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableCircuitBreaker
//@EnableHystrixDashboard
//@EnableCaching
public class UserApplication {
//	@LoadBalanced
//	@Bean
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//
//
//
//	@Bean
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager("users");
//	}
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}
