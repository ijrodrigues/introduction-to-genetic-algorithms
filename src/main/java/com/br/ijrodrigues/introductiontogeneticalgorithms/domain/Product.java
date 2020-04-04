package com.br.ijrodrigues.introductiontogeneticalgorithms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
@AllArgsConstructor
public class Product {
    private String name;
    private Double size;
    private Double price;

    public static ArrayList<Product> getSomeProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Fridge Consul", 0.751, 999.90));
        products.add(new Product("Fridge Brastemp", 0.870, 1199.89));
        products.add(new Product("Iphone 6", 0.000089, 2911.12));
        products.add(new Product("Iphone X", 0.000079, 6500.12));
        products.add(new Product("Bike Shimano", 0.650, 1399.89));
        products.add(new Product("TV 42", 0.300, 1999.00));
        products.add(new Product("TV 50", 0.400, 2999.00));
        products.add(new Product("TV 60", 0.500, 3999.00));
        products.add(new Product("Microwave Electrolux", 0.0544, 429.90));
        products.add(new Product("Microwave Panasonic", 0.0429, 308.90));
        products.add(new Product("Laptop Asus", 0.527, 3999.00));
        products.add(new Product("Laptop Dell", 0.327, 4999.00));
        products.add(new Product("Fan General Eletrics", 0.496, 199.90));
        products.add(new Product("Fan Consul", 0.396, 159.90));
        products.add(new Product("Nike Shocks", 0.196, 359.90));
        return products;
    }
}