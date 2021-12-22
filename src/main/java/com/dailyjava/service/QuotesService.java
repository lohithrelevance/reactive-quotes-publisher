package com.dailyjava.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuotesService {

	@Autowired
	private ResourceLoader resourceLoader;
	
	
	public Mono<String> readFile() throws IOException {
		
		Resource resource = resourceLoader.getResource("classpath:quotes.txt");
		File file = resource.getFile();
		Path path = file.toPath();
		
//		Flux<String> quotesFlux = Flux.using(
//				() -> Files.lines(path),
//				Flux :: fromStream,
//				Stream<String> :: close);
//		
//		
//		Consumer<String> quotes = t -> System.out.println(t);
//		
//		quotesFlux.subscribe(quotes);
//		
		Supplier<String> supplier = () -> "No Quote today !!! Sorry :(";
		
		List<String> listOfQuotes = Files.lines(path).collect(Collectors.toList());
		
		Collections.shuffle(listOfQuotes);
		
		String quote = listOfQuotes.stream().findAny().orElseGet(supplier);
		
		return Mono.just(quote);
	}
}
