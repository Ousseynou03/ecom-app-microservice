package org.sid.orderservice;

import feign.Logger;
import org.sid.orderservice.entities.OrderService;
import org.sid.orderservice.entities.ProductItem;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.sid.orderservice.repository.OrderServiceRepository;
import org.sid.orderservice.repository.ProductItemRepository;
import org.sid.orderservice.service.CustomerRestClient;
import org.sid.orderservice.service.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OrderServiceRepository orderServiceRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClient customerRestClient,
							InventoryRestClient inventoryRestClient){
		return args -> {
			Long customerId=1L;
			Customer customer=customerRestClient.customerById(customerId);
			List<Customer> customers=customerRestClient.allCustomers().getContent().stream().toList();
			List<Product> productList=inventoryRestClient.allProducts().getContent().stream().toList();
			System.out.println(customer.toString());
			System.out.println(productList);
			for (int i = 1; i <10 ; i++) {
				var index=new Random().nextInt(customers.size());
				Customer randomCustomer=customers.get(index);
				OrderService orderService= OrderService.builder().creatDate(new Date()).customerId(randomCustomer.getId()).orderStatus(Math.random()>0.5? OrderStatus.CREATED:OrderStatus.WAITING).build();
				OrderService savedOrder = orderServiceRepository.save(orderService);
				productList.forEach(p->{
					if(Math.random()>0.5){
						ProductItem productItem= ProductItem.builder()
								.productId(p.getId())
								.price(p.getPrice())
								.quantity(1+new Random().nextInt(5))
								.orderService(savedOrder)
								.productId(p.getId())
								.build();
						productItemRepository.save(productItem);
					}
				});
			}
		};

	}

	//Désactivation du CORS
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

}
