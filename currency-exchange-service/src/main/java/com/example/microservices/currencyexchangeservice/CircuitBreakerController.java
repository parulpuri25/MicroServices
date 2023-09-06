package com.example.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
	//@Retry(name="sample-api", fallbackMethod="hardcodedResponse")
	//@CircuitBreaker(name="default", fallbackMethod="hardcodedResponse")
	//@RateLimiter(name="default")
	@Bulkhead(name="default")
	public String sampleApi() {
		logger.info("Sample API call received");
		return "Sample APi";
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8900",String.class);
//		return forEntity.getBody();
	}
	
	public String hardcodedResponse(Exception ex) {
			return "fallback-Response";
	}
}

