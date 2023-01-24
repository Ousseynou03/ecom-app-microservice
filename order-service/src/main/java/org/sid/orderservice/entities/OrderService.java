package org.sid.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderService {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date creatDate;
    private OrderStatus orderStatus;
    private Long customerId;
    @Transient private Customer customer;
    @OneToMany(mappedBy = "orderService")
    private List<ProductItem> productItems;
}
