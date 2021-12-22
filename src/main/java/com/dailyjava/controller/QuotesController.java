package com.dailyjava.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailyjava.service.QuotesService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/quotes")
public class QuotesController {

	@Autowired
	private QuotesService qService;
	
	@GetMapping("/api")
	public Mono<String> test() {
		
		try {
			Mono<String> quote = qService.readFile();
			quote.subscribe(System.out :: println);
			return quote;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Mono.empty();
	}
}
