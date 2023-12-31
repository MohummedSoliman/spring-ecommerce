package com.mohamed.springbootecommerce.dao;

import com.mohamed.springbootecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);
}
