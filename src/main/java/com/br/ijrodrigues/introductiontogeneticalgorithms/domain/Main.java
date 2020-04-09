package com.br.ijrodrigues.introductiontogeneticalgorithms.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Main {

    public static void main(String[] args) {
        ArrayList<Product> products = Product.getSomeProducts();
        Population population = new Population(20, products, 3.0);
        population.run(500, 1);
    }
}