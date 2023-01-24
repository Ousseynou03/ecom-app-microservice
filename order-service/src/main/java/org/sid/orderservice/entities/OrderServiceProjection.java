package org.sid.orderservice.entities;

import org.sid.orderservice.enums.OrderStatus;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "orderServiceProj1", types = OrderService.class)
public interface OrderServiceProjection {
    Long getId();
    Date getCreatDate();
    Long getCustomerId();
    OrderStatus getOrderStatus();


}
