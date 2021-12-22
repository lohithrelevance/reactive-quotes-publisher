package com.dailyjava;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "36000")
@ExtendWith(SpringExtension.class)
public class RandomQuotesTest {

	
	@Autowired
	private WebTestClient webTestClient;
	
	@RepeatedTest(10)
	public void print() {
		
		webTestClient
		.get()
		.uri("/quotes/api")
		.exchange()
		.expectStatus()
		.isOk();
	}
	
	
}
