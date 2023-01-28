package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            List.of(
                    Customer.builder().name("Dione").email("dioneousseynou03@gmail.com").phoneNum("0705443903").address("Casa, Sidi Maarouf").build(),
                    Customer.builder().name("Ousseynou").email("ouzin03@gmail.com").phoneNum("0705446703").address("Casa, 2Mars").build(),
                    Customer.builder().name("Saad").email("saad@gmail.com").phoneNum("0705443998").address("Casa,Maariif").build(),
                    Customer.builder().name("Reda").email("reda@gmail.com").phoneNum("0705563903").address("Berrechid").build(),
                    Customer.builder().name("Jad").email("jad@gmail.com").phoneNum("0705532903").address("Rabat").build()
            ).forEach(customer -> {
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(System.out::println);
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


}
