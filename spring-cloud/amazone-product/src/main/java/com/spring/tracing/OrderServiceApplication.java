package com.spring.tracing;

import com.spring.tracing.model.Product;
import com.spring.tracing.service.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class OrderServiceApplication implements CommandLineRunner{
@Autowired
AddProductService addProductService;
	public static void main(String[] args)  {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}


	@Override
	public void run(String... args) throws Exception {
		Product p1=new Product();
		p1.setCategory("skirt");
		p1.setDescription("skirt beautiful");
		p1.setName("product 1");
		p1.setOwnerEmail("salma@gmail.com");
		p1.setPrice(1222);
		addProductService.save(p1);
	}

}
