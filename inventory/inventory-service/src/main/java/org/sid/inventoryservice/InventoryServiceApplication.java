package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;


@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            for (int i = 0; i <3 ; i++) {
                productRepository.save(Product.builder().name("Burgure Frite "+i).quantity(34).price(27).description("Burgure frite").imageUrl("https://i.ibb.co/WWnjDdG/burger1.jpg").build());
                productRepository.save(Product.builder().name("Burgure salade "+i).quantity(11).price(50).description("Burgure salade").imageUrl("https://i.ibb.co/LS2T9sD/burger2.jpg").build());
                productRepository.save(Product.builder().name("Burgure "+i).quantity(13).price(25).description("Burgure").imageUrl("https://i.ibb.co/LS2T9sD/burger2.jpg").build());
                productRepository.save(Product.builder().name("Chawarma "+i).quantity(10).price(30).description("Burgure").imageUrl("https://i0.wp.com/www.lesdelicesdejessy.com/wp-content/uploads/2019/10/profil.jpg?fit=1060%2C707&ssl=1").build());

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
}





