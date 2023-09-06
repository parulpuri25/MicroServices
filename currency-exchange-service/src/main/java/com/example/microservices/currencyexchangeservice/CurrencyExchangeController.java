package com.example.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment environment;
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository; 
	
	public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
		super();
		this.environment = environment;
		this.currencyExchangeRepository = currencyExchangeRepository;
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrievedExchangeValue(@PathVariable String from,
			@PathVariable String to) {
//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L,from,to,
//						BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange 
			= currencyExchangeRepository.findByFromAndTo(from, to);
		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find data for "+from+" to "+to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
	
	
	
}
