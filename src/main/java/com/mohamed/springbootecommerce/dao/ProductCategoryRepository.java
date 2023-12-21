package com.mohamed.springbootecommerce.dao;

import com.mohamed.springbootecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
