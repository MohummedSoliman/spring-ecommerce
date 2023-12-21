package com.mohamed.springbootecommerce.service;

import com.mohamed.springbootecommerce.dao.CustomerRepository;
import com.mohamed.springbootecommerce.dto.Purchase;
import com.mohamed.springbootecommerce.dto.PurchaseResponse;
import com.mohamed.springbootecommerce.entity.Customer;
import com.mohamed.springbootecommerce.entity.Order;
import com.mohamed.springbootecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();

        String orderTrackingNumber = generateOrderTrackingNumber();

        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();

        orderItems.forEach(item -> order.add(item));

        order.setBillingAddress(purchase.getBillingAddress());

        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();

        // Check if there is an existence customer
        Customer customerFromDB = customerRepository.findByEmail(customer.getEmail());

        // this is older customer
        if (customerFromDB != null){
            customer = customerFromDB;
        }

        customer.add(order);

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
