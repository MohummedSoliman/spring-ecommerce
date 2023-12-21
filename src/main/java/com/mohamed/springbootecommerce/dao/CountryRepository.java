package com.mohamed.springbootecommerce.dao;

import com.mohamed.springbootecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@RepositoryRestResource(path = "countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
