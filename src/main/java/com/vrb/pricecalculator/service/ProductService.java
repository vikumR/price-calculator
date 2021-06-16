package com.vrb.pricecalculator.service;

import com.vrb.pricecalculator.entity.Product;
import com.vrb.pricecalculator.exception.ProductNotFoundException;
import com.vrb.pricecalculator.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct (Product product) {
        productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Product findProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow( () -> new ProductNotFoundException("Product by id " + id + " not found"));
    }

}
