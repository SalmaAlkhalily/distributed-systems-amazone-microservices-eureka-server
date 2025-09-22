package com.spring.tracing.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.tracing.model.Orders;
import com.spring.tracing.service.OrderingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    OrderingService orderingService=new OrderingService();
    @GetMapping(value = "/getorder")
    @HystrixCommand(fallbackMethod = "getDataFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
    })
    public float addOrder(){


        log.info("GET /orders");


       // String M3Response=restTemplate.getForObject("http://user-service/user/getemail",String.class);
Orders orders=new Orders();
orders.setOrderDate(new Date(12,12,2019));
orders.setProductId(1);
        if(orders.getPrice()==1222)
        {
            throw  new RuntimeException();
        }

       /* if(o.getPrice()==11)
        {
            throw  new RuntimeException();
        }*/
return         orderingService.save(orders);
        //return "helllllllll";
    }

    public float getDataFallBack(){
        log.info("GET /orders");


        // String M3Response=restTemplate.getForObject("http://user-service/user/getemail",String.class);
        Orders orders=new Orders();
        orders.setOrderDate(new Date(12,12,2019));
        orders.setProductId(1);


        orderingService.save(orders);
        return  12223;
        //return "helllllllll";

    }


}
