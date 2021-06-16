package com.vrb.pricecalculator.service;

import com.vrb.pricecalculator.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@Service
public class PrizeService {

    private final ProductService productService;

    @Autowired
    public PrizeService(ProductService productService) {
        this.productService = productService;
    }

    public double calculateUnitPrice(double cartonPrice, int unitsPerCarton) {
        return (cartonPrice + (cartonPrice * ((double) 30 / 100))) / (double) unitsPerCarton;
    }

    public double calculateCartonDiscount(int cartons, double cartonPrice) {
        if (cartons >= 3)
            return (double) cartons * cartonPrice * ((double) 10 / 100);
        else return 0;
    }

    public double calculateTotalCartonPrice(int cartons, double cartonPrice, double cartonDiscount) {
        return (cartons * cartonPrice) - cartonDiscount;
    }

    public double calculateTotalUnitPrice(int units, double unitPrice) {
        return units * unitPrice;
    }

    public double calculateSubTotal(Long productId, int units, int cartons) {
        //handle null pointer exception
        Product product = productService.findProductById(productId);
        double unitPrice = calculateUnitPrice(product.getCartonPrice(), product.getUnitsPerCarton());
        double cartonDiscount = calculateCartonDiscount(cartons, product.getCartonPrice());
        return calculateTotalUnitPrice(units, unitPrice) + calculateTotalCartonPrice(cartons, product.getCartonPrice(), cartonDiscount);
    }

    public double calculateTotal(List<Product> productsList) {
        double total = 0;
        for (Product product : productsList) {
            total = calculateSubTotal(product.getId(), product.getUnits(), product.getCartons());
        }
        return total;
    }
}
