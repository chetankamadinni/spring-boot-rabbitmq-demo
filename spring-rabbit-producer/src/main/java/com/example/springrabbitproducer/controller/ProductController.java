package com.example.springrabbitproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrabbitproducer.model.Product;
import com.example.springrabbitproducer.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService ps;

	@PostMapping("/product")
	public ResponseEntity postProduct(@RequestBody Product product) {
		ps.postProduct(product);
		return new ResponseEntity(HttpStatus.OK);
	}

}
