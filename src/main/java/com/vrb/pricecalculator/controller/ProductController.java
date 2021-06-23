package com.vrb.pricecalculator.controller;

import com.vrb.pricecalculator.data.DataMap;
import com.vrb.pricecalculator.data.DataModel;
import com.vrb.pricecalculator.entity.Product;
import com.vrb.pricecalculator.exception.ProductNotFoundException;
import com.vrb.pricecalculator.service.PrizeService;
import com.vrb.pricecalculator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final PrizeService prizeService;
    private final DataMap dataMap;

    @Autowired
    public ProductController(ProductService productService, PrizeService prizeService, DataMap dataMap) {
        this.productService = productService;
        this.prizeService = prizeService;
        this.dataMap = dataMap;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DataModel>> getProductById(@PathVariable("id") Long id) {
        Product product;
        try{
            product = productService.findProductById(id);
            List<DataModel> dataSource = dataMap.getDataSource(product);
            return new ResponseEntity<>(dataSource, HttpStatus.OK);

        }catch (ProductNotFoundException e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/total")
    public ResponseEntity<Double> calculateTotal(@RequestBody List<Product> products) {
        double total = prizeService.calculateTotal(products);
        System.out.println("Calculated Total Prices = " + total);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
