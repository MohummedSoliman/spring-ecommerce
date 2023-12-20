package com.mohamed.springbootecommerce.service;

import com.mohamed.springbootecommerce.dto.Purchase;
import com.mohamed.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
