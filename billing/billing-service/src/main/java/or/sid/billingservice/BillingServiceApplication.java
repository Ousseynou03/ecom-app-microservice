package or.sid.billingservice;

import feign.Logger;
import or.sid.billingservice.entities.Bill;
import or.sid.billingservice.entities.ProductItem;
import or.sid.billingservice.model.Customer;
import or.sid.billingservice.model.Product;
import or.sid.billingservice.repository.BillRepository;
import or.sid.billingservice.repository.ProductItemRepository;
import or.sid.billingservice.services.CustomerRestClient;
import or.sid.billingservice.services.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
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
            for (int i = 1; i <20 ; i++) {
                var index=new Random().nextInt(customers.size());
                Customer randomCustomer=customers.get(index);
                Bill bill= Bill.builder().createdAt(new Date()).customerId(randomCustomer.getId()).build();
                Bill savedBill = billRepository.save(bill);
                productList.forEach(p->{
                    if(Math.random()>0.5){
                        ProductItem productItem= ProductItem.builder()
                                .productId(p.getId())
                                .price(p.getPrice())
                                .quantity(1+new Random().nextInt(5))
                                .bill(savedBill)
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
