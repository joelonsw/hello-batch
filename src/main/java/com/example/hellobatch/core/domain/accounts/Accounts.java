package com.example.hellobatch.core.domain.accounts;

import com.example.hellobatch.core.domain.orders.Orders;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderItem;
    private Integer price;
    private Date orderDate;
    private Date accountDate;

    public static Accounts from(Orders orders) {
        return new Accounts(
            orders.getId(),
            orders.getOrderItem(),
            orders.getPrice(),
            orders.getOrderDate(),
            new Date()
        );
    }
}
