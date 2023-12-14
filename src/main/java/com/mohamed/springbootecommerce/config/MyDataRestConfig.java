package com.mohamed.springbootecommerce.config;

import com.mohamed.springbootecommerce.entity.Product;
import com.mohamed.springbootecommerce.entity.ProductCategory;
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
        HttpMethod[] theUnSupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT};

        // Disable Http method --> PUT, POST AND DELETE
        // Make Product READ_ONLY
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions));

        // Disable Http method --> PUT, POST AND DELETE
        // Make Product READ_ONLY
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions));

        // This is for expose ids for all entities so Spring Data Rest not expose ids by default.
        exposeIdsForAllEntities(config);
    }

    private void exposeIdsForAllEntities(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        entities.forEach(entity -> config.exposeIdsFor(entity.getJavaType()));
    }
}
