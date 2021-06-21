package com.example.comsumingrest;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PredictionController {
	private static final String template = "Hello, %s";
	private final AtomicLong counter = new AtomicLong();
	private static final String predictionURL = "http://10.2.117.32:5000";
	
	@Bean
	@RequestMapping("/Quote")
	String getQuote() {
		RestTemplate restTemplate = new RestTemplate();
		Quote quote = restTemplate.getForObject(
				"https://quoters.apps.pcfone.io/api/random",
				Quote.class);
		//return quote.toString() + counter.incrementAndGet();
		return quote.toString();
	}
	
	public String postPrediction(String image) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<PredictionSource> request = new HttpEntity<>(new PredictionSource(counter.get(), image));
		
		PredictionResult result = restTemplate.postForObject(predictionURL + "/predict", request, PredictionResult.class);

		//return quote.toString() + counter.incrementAndGet();
		return result.toString();
	}
}