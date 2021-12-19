package com.restaurant.UserService.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${restaurant.message.}")
    private String host;

    @Bean
    public Queue UserCommandsQueue() {
        return new Queue("asdasd", true);
    }
}
