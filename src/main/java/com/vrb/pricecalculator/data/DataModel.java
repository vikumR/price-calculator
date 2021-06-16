package com.vrb.pricecalculator.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataModel {
    private int cartons;
    private double cartonPrice;
    private int units;
    private double unitPrice;

    public DataModel(int cartons, double cartonPrice, int units, double unitPrice) {
        this.cartons = cartons;
        this.cartonPrice = cartonPrice;
        this.units = units;
        this.unitPrice = unitPrice;
    }
}
