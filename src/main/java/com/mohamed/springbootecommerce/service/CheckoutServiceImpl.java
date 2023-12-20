package com.mohamed.springbootecommerce.service;

import com.mohamed.springbootecommerce.dao.CustomerRepository;
import com.mohamed.springbootecommerce.dto.Purchase;
import com.mohamed.springbootecommerce.dto.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        return null;
    }
}
