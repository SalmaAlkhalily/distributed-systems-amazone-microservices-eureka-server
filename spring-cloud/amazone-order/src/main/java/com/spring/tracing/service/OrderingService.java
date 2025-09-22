package com.spring.tracing.service;
import com.spring.tracing.model.Orders;
import com.spring.tracing.model.User;
import com.spring.tracing.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderingService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    RestTemplate restTemplate;
    public List<Orders> getAllOrders() {

        List<Orders> orders = new ArrayList<>();
        ordersRepository.findAll().forEach(order -> orders.add(order));
        return orders;
    }

    public void delete(Integer id) {
        ordersRepository.deleteById(id);
    }
    public Optional< Orders> getOrderById(int id) {
        return ordersRepository.findById(id);
    }

    public float save(Orders order) {
        User user=new User("salma","alkhalili","salma@gmail.com","123");
      //  userService.createAccount(user);
        userService.Login(user);
        order.setBuyerEmail(user.getEmail());
       order.setProductId(1);
        order.setPrice( productService.getProductPrice(order.getId()));
    //    String M2Response=restTemplate.postForObject("http://product-service/product/getprice",product,String.class);
        ordersRepository.save(order);
        return order.getPrice();
    }
}
