package com.vrb.pricecalculator;

import com.vrb.pricecalculator.entity.Product;
import com.vrb.pricecalculator.repository.ProductRepository;
import com.vrb.pricecalculator.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestProductService {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findProductByIdTest() {
        Product product = new Product("Penguin-ears", 175.0, 20);
        when(repository.findById(2L)).thenReturn(Optional.of(product));

        Product product1 = productService.findProductById(2L);

        assertEquals("Horseshoe", product1.getProductName());
    }

    @Test
    public void findAllProductsTest() {
        List<Product> list = new ArrayList<>();

        Product product1 = new Product("Penguin-ears", 175.0, 20);
        Product product2 = new Product("Horseshoe", 825.0, 5);

        list.add(product1);
        list.add(product2);

        when(repository.findAll()).thenReturn(list);

        List<Product> productList = productService.findAllProducts();

        assertEquals(2, productList.size());
        verify(repository, times(1)).findAll();
    }
}
