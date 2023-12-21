package com.mohamed.springbootecommerce.config;

import com.mohamed.springbootecommerce.entity.Country;
import com.mohamed.springbootecommerce.entity.Product;
import com.mohamed.springbootecommerce.entity.ProductCategory;
import com.mohamed.springbootecommerce.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {


        // Disable Http method --> PUT, POST AND DELETE
        disableHttpMethods(config, Product.class);

        disableHttpMethods(config, ProductCategory.class);

        disableHttpMethods(config, Country.class);

        disableHttpMethods(config, State.class);

        // This is for expose ids for all entities so Spring Data Rest not expose ids by default.
        exposeIdsForAllEntities(config);

        cors.addMapping("/api/**").allowedOrigins("http://172.17.0.2:4200");
    }

    private void disableHttpMethods(RepositoryRestConfiguration config, Class domain){
        HttpMethod[] theUnSupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT};
        config.getExposureConfiguration()
                .forDomainType(domain)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnSupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnSupportedActions));
    }

    private void exposeIdsForAllEntities(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        entities.forEach(entity -> config.exposeIdsFor(entity.getJavaType()));
    }
}
