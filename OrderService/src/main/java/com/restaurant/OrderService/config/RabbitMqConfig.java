package com.restaurant.OrderService.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.OrderService.adapters.outgoing.message.EventPublisher;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${message.project-exchange}")
    private String projectExchange;

    @Value("${message.queue.user-event}")
    private String userEventQueue;
    @Value("${message.queue.user-event-binding}")
    private String userEventBinding;

    @Value("${message.queue.restaurant-event}")
    private String restaurantEventQueue;
    @Value("${message.queue.restaurant-event-binding}")
    private String restaurantEventBinding;

    @Value("${message.queue.table-event}")
    private String tableEventQueue;
    @Value("${message.queue.table-event-binding}")
    private String tableEventBinding;


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(projectExchange);
    }

    // Register order event publisher
    @Bean
    public EventPublisher orderEventPublisher(RabbitTemplate template) {
        return new EventPublisher(projectExchange, template);
    }

    // Register user event listener
    @Bean
    public Queue userEventListeningQueue() {
        return new Queue(userEventQueue, true);
    }
    @Bean
    public Binding userEventListeningBinding(Queue userEventListeningQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userEventListeningQueue).to(exchange).with(userEventBinding);
    }

    // Register restaurant event listener
    @Bean
    public Queue restaurantEventListeningQueue() {
        return new Queue(restaurantEventQueue, true);
    }
    @Bean
    public Binding restaurantEventListeningBinding(Queue restaurantEventListeningQueue, TopicExchange exchange) {
        return BindingBuilder.bind(restaurantEventListeningQueue).to(exchange).with(restaurantEventBinding);
    }

    // Register table event listener
    @Bean
    public Queue tableEventListeningQueue(){
        return new Queue(tableEventQueue, true);
    }
    @Bean
    public Binding tableEventListeningBinding(Queue tableEventListeningQueue, TopicExchange exchange){
        return BindingBuilder.bind(tableEventListeningQueue).to(exchange).with(tableEventBinding);
    }

    // Setup RabbitMQ communication
    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);
        converter.setAlwaysConvertToInferredType(true);
        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }
}
