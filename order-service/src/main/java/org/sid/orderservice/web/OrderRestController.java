package org.sid.orderservice.web;

import org.sid.orderservice.entities.OrderService;
import org.sid.orderservice.entities.ProductItem;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.sid.orderservice.repository.OrderServiceRepository;
import org.sid.orderservice.repository.ProductItemRepository;
import org.sid.orderservice.service.CustomerRestClient;
import org.sid.orderservice.service.InventoryRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private final OrderServiceRepository orderServiceRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerRestClient;
    private final InventoryRestClient inventoryRestClient;


    public OrderRestController(OrderServiceRepository orderServiceRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, InventoryRestClient inventoryRestClient) {
        this.orderServiceRepository = orderServiceRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.inventoryRestClient = inventoryRestClient;
    }

    @GetMapping("/fullOrder/{id}")
    public OrderService getOrderService(@PathVariable Long id){
        OrderService orderService=orderServiceRepository.findById(id).get();
        Customer customer=customerRestClient.customerById(orderService.getCustomerId());
        orderService.setCustomer(customer);
        for(ProductItem pi:orderService.getProductItems()){
            Product product=inventoryRestClient.productById(pi.getProductId());
            System.out.println(product);
            pi.setProduct(product);
        }
        return orderService;
    }
}
