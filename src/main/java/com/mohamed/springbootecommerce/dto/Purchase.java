package com.mohamed.springbootecommerce.dto;

import com.mohamed.springbootecommerce.entity.Address;
import com.mohamed.springbootecommerce.entity.Customer;
import com.mohamed.springbootecommerce.entity.Order;
import com.mohamed.springbootecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
