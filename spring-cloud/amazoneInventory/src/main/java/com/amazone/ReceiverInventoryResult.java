package com.amazone;
import com.amazone.model.Inventory;
import com.amazone.service.InventoryService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;


@Component
public class ReceiverInventoryResult {
    public static final String exchange = "amazone-exchange";
    static final String queueName = "InventoryQueue";
    @Autowired
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("amazone.inventory");
    }
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    @Bean
    MessageListenerAdapter listenerAdapter(MyReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveing");
    }
}
@Component
class MyReceiver {
    @Autowired
    private InventoryService inventoryService;
    private CountDownLatch latch = new CountDownLatch(1);
    public void receiveing(String message) {
        // Product product=new Product(message.split(",")[0],message.split(",")[1],message.split(",")[2],Integer.parseInt(message.split(",")[3]),message.split(",")[0]);

        Date resd=inventoryService.convertStringToDate(message);
        Inventory requested=inventoryService.getInventoryForDate(resd);
        System.out.println("Received ------------>>>>>>>>"+"date: "+resd);
        System.out.println("Result of Requested date ------------>>>>>>>>"+"inventory: "+requested);

        latch.countDown();
    }
    public CountDownLatch getLatch() {
        return latch;
    }
}