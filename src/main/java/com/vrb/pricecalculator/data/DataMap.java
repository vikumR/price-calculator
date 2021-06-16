package com.vrb.pricecalculator.data;

import com.vrb.pricecalculator.entity.Product;
import com.vrb.pricecalculator.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataMap {

    private final PrizeService prizeService;

    @Autowired
    public DataMap(PrizeService prizeService) {
        this.prizeService = prizeService;
    }

    public List<DataModel> getDataSource(Product product) {
        List<DataModel> priceList = new ArrayList<>();

        int unitsPerCarton = product.getUnitsPerCarton();
        int cartons = 0;
        double cartonPrize = product.getCartonPrice();
        double unitPrice = prizeService.calculateUnitPrice(cartonPrize, unitsPerCarton);

        int numUnits = 1;

        while (numUnits <= 50) {

            var cartonDisCount = prizeService.calculateCartonDiscount(cartons, cartonPrize);
            var totalCartonPrice = prizeService.calculateTotalCartonPrice(cartons, cartonPrize, cartonDisCount);
            var totalUnitPrice = prizeService.calculateTotalUnitPrice(numUnits, unitPrice);

            DataModel row = new DataModel(cartons, totalCartonPrice, numUnits, totalUnitPrice);

            priceList.add(row);
            cartons++;
            numUnits = cartons * product.getUnitsPerCarton();
        }
        return priceList;
    }

}
