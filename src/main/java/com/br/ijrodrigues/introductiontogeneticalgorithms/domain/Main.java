package com.br.ijrodrigues.introductiontogeneticalgorithms.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Main {

    public static void main(String[] args) {
        ArrayList<Product> products = Product.getSomeProducts();

        Population population = new Population(100);
        population.initialize(products, 3.0);

        log.info("best solution found: {}", population.getBestIndividual());
    }
}