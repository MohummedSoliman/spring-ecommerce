package com.mohamed.springbootecommerce.controller;

import com.mohamed.springbootecommerce.dto.Purchase;
import com.mohamed.springbootecommerce.dto.PurchaseResponse;
import com.mohamed.springbootecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase){
        PurchaseResponse order = this.checkoutService.placeOrder(purchase);
        return new ResponseEntity<>(
                new PurchaseResponse(order.getOrderTrackingNumber()),
                HttpStatus.CREATED);
    }
}
