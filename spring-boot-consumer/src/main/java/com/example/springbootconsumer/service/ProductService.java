package com.example.springbootconsumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.springbootconsumer.model.Product;

@Component
public class ProductService {

	@RabbitListener(queues = "${rabbitmq.queueName}")
	public void getProduct(Product product) {
		System.out.println("Product details : " + product);
	}
}
