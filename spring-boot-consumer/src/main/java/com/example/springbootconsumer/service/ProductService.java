package com.example.springbootconsumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.springbootconsumer.exception.InvalidProductException;
import com.example.springbootconsumer.model.Product;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductService {

	@RabbitListener(queues = "${rabbitmq.queueName}")
	public void getProduct(Product product) throws InvalidProductException {
		log.info("Product details : " + product);
		if(product.getProductId()<=0) {
			throw new InvalidProductException();
		}
	}
}
