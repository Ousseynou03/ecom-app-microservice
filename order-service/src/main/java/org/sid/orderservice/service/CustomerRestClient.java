package org.sid.orderservice.service;


import org.sid.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    
    @GetMapping(path = "/customers/{id}?projection=customerProj1")
    public Customer customerById(@PathVariable Long id);
    @GetMapping(path = "/customers?projection=customerProj1")
    public PagedModel<Customer> allCustomers();
}