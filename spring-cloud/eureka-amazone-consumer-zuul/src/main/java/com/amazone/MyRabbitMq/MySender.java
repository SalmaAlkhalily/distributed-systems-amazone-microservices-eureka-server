package com.amazone.MyRabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import java.util.concurrent.CountDownLatch;

@Component
public class MySender {
    public static final String exchange = "amazone-exchange";
    static final String queueName = "InventoryQueue";
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
        //container.setMessageListener(listenerAdapter);
        return container;
    }
    @Bean
    MessageListenerAdapter listenerAdapter(MyReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveing");
    }

}
@Component
class MyReceiver {
    private CountDownLatch latch = new CountDownLatch(1);
    public void receiveing(Object message) {
        latch.countDown();
    }
    public CountDownLatch getLatch() {
        return latch;
    }
}