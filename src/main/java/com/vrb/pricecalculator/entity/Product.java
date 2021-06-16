package com.vrb.pricecalculator.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String productName;

    @Column(nullable = false)
    private Double cartonPrice;

    @Column(nullable = false)
    private Integer unitsPerCarton;

    @Transient
    private Integer units;

    @Transient
    private Integer cartons;


    public Product() {
    }

    public Product(String productName, Double cartonPrice, Integer unitsPerCarton) {
        this.productName = productName;
        this.cartonPrice = cartonPrice;
        this.unitsPerCarton = unitsPerCarton;
    }
}
