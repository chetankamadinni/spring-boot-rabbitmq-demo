package com.example.springrabbitproducer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Value("${rabbitmq.routingKey}")
	public String routingKey;

	@Value("${rabbitmq.queueName}")
	public String queueName;

	@Value("${rabbitmq.exchange}")
	public String exchange;
	
	@Value("${rabbitmq.dlqExchange}")
	public String deadLetterExchange;
	
	@Value("${rabbitmq.dlqName}")
	public String dlq;

	@Value("${rabbitmq.dlqroutingKey}")
	private String dlrk;

	@Bean
	Queue dlq() {
		return new Queue(dlq, true);
	}
	
	@Bean
	Queue queue() {
		return QueueBuilder.durable(queueName).withArgument("x-dead-letter-exchange", deadLetterExchange)
				.withArgument("x-dead-letter-routing-key", dlrk)
				.build();
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	
	@Bean
	DirectExchange deadLetterExchange() {
		return new DirectExchange(deadLetterExchange);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
	
	@Bean
	Binding dlqBinding() {
		return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with(dlrk);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	AmqpTemplate reddisTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
