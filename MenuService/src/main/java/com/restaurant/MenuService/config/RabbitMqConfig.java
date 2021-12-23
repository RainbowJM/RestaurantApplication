package com.restaurant.MenuService.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.MenuService.adapters.outgoing.message.EventPublisher;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.swing.event.MenuEvent;

@Configuration
public class RabbitMqConfig {
	@Value("${spring.rabitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.port}")
	private int port;

	@Value("${message.project-exchange}")
	private String projectExchange;

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(projectExchange);
	}

	// Register user event publisher
	@Bean
	public EventPublisher TableEventPublisher(RabbitTemplate template) {
		return new EventPublisher(projectExchange, template);
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
