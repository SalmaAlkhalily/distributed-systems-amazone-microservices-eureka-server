package com.amazone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.amazone.MyRabbitMq.MySender;
import com.amazone.entity.Orders;
import com.amazone.entity.Product;
import com.amazone.entity.Search;
import com.amazone.entity.User;
import com.amazone.service.ConsumerServices;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class ConsumerApplication implements CommandLineRunner {
	@Autowired
	MySender s;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;
	private ConsumerServices consumerServices=new ConsumerServices();

	public static void main(String[] args)  {
		SpringApplication.run(ConsumerApplication.class, args);
		
	}

	@Override
	@HystrixCommand(fallbackMethod = "getAllUsersFallBack",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "50"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
			})
	public void run(String... strings) throws Exception {
		//////user
		System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
		List<ServiceInstance> instances = discoveryClient.getInstances("AMAZONE-ZUUL-SERVICE");
		ServiceInstance serviceInstance = instances.get(0
		);
		String zuulUrl = serviceInstance.getUri().toString();
		String addUserUrl = zuulUrl + "/user/users/add";
		String loginUserUrl = zuulUrl + "/user/users/login";
		String viewAllUsersUrl=zuulUrl + "/user/users";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		RestTemplate createAccount = new RestTemplate();
		RestTemplate login = new RestTemplate();
		RestTemplate viewAllUsers = new RestTemplate();
		User user=new User("salmakh", "alkhaliliaaa", "fjfjmmmmhhf@gmail.com", "12333");
		HttpEntity<User> httpEntityUser = new HttpEntity<>(user, headers);
		ResponseEntity<String> responseEntitycreateAccount = null;
		ResponseEntity<String> responseEntitlogin = null;
		ResponseEntity<String> responseEntityviewAllUsers = null;
		try {
			responseEntitycreateAccount = createAccount.exchange(addUserUrl,HttpMethod.POST,httpEntityUser,String.class);
			System.out.println("responseEntitycreateAccount:-->>>  "+responseEntitycreateAccount.getBody());
			responseEntitlogin = login.exchange(loginUserUrl,HttpMethod.POST,httpEntityUser,String.class);
			System.out.println("responseEntitlogin:-->>>  "+responseEntitlogin.getBody());
			responseEntityviewAllUsers = viewAllUsers.exchange(viewAllUsersUrl,HttpMethod.GET,httpEntityUser,String.class);
			System.out.println("responseEntityviewAllUsers:-->>>  "+responseEntityviewAllUsers.getBody());
		} catch (Exception ex) {
			System.out.println("Exception:   "+ex);
		}
//////product

		String addProductUrl = zuulUrl + "/product/products/AddProduct";
		String viewAllProductsUrl=zuulUrl + "/product/products";

		RestTemplate addProduct = new RestTemplate();
		RestTemplate viewAllProducts = new RestTemplate();
		Product product=new Product(   "skirt", "beautiful skirt","clothes",12, user.getEmail());
		HttpEntity<Product> httpEntityProduct = new HttpEntity<>(product, headers);
		ResponseEntity<String> responseEntityaddProduct = null;
		ResponseEntity<String> responseEntityviewAllProducts = null;
		try {
			responseEntityaddProduct = addProduct.exchange(addProductUrl,HttpMethod.POST,httpEntityProduct,String.class);
			System.out.println("responseEntityaddProduct:-->>>  "+responseEntityaddProduct.getBody());

			responseEntityviewAllProducts = viewAllProducts.exchange(viewAllProductsUrl,HttpMethod.GET,httpEntityProduct,String.class);
			System.out.println("responseEntityviewAllProducts:-->>>  "+responseEntityviewAllProducts.getBody());
		} catch (Exception ex) {
			System.out.println("Exception:   "+ex);
		}
		//////search

		String searchtUrl = zuulUrl + "/search/search";
		RestTemplate searchProduct = new RestTemplate();
		Search searchQuery=new Search(1,"skirt");
		HttpEntity<Search> httpEntitySearch = new HttpEntity<>(searchQuery, headers);
		ResponseEntity<String> responseEntitysearchProduct = null;
		try {
			responseEntitysearchProduct = searchProduct.exchange(searchtUrl,HttpMethod.POST,httpEntitySearch,String.class);
			System.out.println("responseEntitysearchProduct:-->>>  "+responseEntitysearchProduct.getBody());
			ObjectMapper mapper = new ObjectMapper();
			List<Product> products = Arrays.asList(mapper.readValue(responseEntityviewAllProducts.getBody(), Product[].class));
			List<Product> searchRes=consumerServices.findProduct(searchQuery,products,responseEntitysearchProduct.getBody());
			System.out.println("searchRes         "+searchRes);

		} catch (Exception ex) {
			System.out.println("Exception:   "+ex);
		}

		//order
		String addOrderUrl = zuulUrl + "/order/orders/AddOrder";
		String viewAllOrdersUrl=zuulUrl + "/order/orders";
		RestTemplate addOrder = new RestTemplate();
		RestTemplate viewAllOrders = new RestTemplate();
		Date d=new Date(12,12,2019);
		Orders order=new Orders(   Integer.parseInt(responseEntityaddProduct.getBody()),product.getPrice(), user.getEmail(),d);
		HttpEntity<Orders> httpEntityOrders = new HttpEntity<>(order, headers);
		ResponseEntity<String> responseEntityaddOrder = null;
		ResponseEntity<String> responseEntityviewAllOrders = null;
		try {
			responseEntityaddOrder = addOrder.exchange(addOrderUrl,HttpMethod.POST,httpEntityOrders,String.class);
			System.out.println("responseEntityaddOrder:-->>>  "+responseEntityaddOrder.getBody());

			responseEntityviewAllOrders = viewAllOrders.exchange(viewAllOrdersUrl,HttpMethod.GET,httpEntityOrders,String.class);
			System.out.println("responseEntityviewAllOrders:-->>>  "+responseEntityviewAllOrders.getBody());
		} catch (Exception ex) {
			System.out.println("Exception:   "+ex);
		}

//inventory

		String inventory="12"+"-"+"12"+"-"+"2019";
		rabbitTemplate.convertAndSend(s.exchange, "foo.bar.baz",inventory );

		System.out.println("inventory from sender....    "+inventory);



	}
	public void getAllUsersFallBack(String... strings) {
		System.out.println("user service shutdown");

	}
}
