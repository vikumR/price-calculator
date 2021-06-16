package com.vrb.pricecalculator.bootstrap;

import com.vrb.pricecalculator.entity.Product;
import com.vrb.pricecalculator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final ProductService productService;

    @Autowired
    public BootstrapData(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product("Penguin-ears", 175.0, 20);
        Product product2 = new Product("Horseshoe", 825.0, 5);

        productService.addProduct(product1);
        productService.addProduct(product2);
    }
}
