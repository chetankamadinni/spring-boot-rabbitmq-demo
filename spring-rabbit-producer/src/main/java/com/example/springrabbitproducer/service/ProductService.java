package com.example.springrabbitproducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.springrabbitproducer.model.Product;

@Service
public class ProductService {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Value("${rabbitmq.exchange}")
	public String exchange;

	public void postProduct(Product product) {
		rabbitTemplate.convertAndSend(exchange, "foo.bar.baz", product);
	}

}
