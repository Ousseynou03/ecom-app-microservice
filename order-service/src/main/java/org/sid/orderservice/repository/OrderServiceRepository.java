package org.sid.orderservice.repository;

import org.sid.orderservice.entities.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.List;

@RepositoryRestResource
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {

    //Liste des commandes qu'un client a effectué
    @RestResource(path = "/ordersByCustomerId")
    List<OrderService> findByCustomerId(@Param("id")Long customerId);
}
