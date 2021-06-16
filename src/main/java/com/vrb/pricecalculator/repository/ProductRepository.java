package com.vrb.pricecalculator.repository;

import com.vrb.pricecalculator.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductName(String productName);

//    Optional<Product> findProductById(Long id);
}
