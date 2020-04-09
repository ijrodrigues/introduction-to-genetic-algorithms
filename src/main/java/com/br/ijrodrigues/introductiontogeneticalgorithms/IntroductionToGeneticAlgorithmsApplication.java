package com.br.ijrodrigues.introductiontogeneticalgorithms;

import com.br.ijrodrigues.introductiontogeneticalgorithms.domain.Population;
import com.br.ijrodrigues.introductiontogeneticalgorithms.domain.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

//@SpringBootApplication
public class IntroductionToGeneticAlgorithmsApplication {

    public static void main(String[] args) {
//        SpringApplication.run(IntroductionToGeneticAlgorithmsApplication.class, args);

        ArrayList<Product> products = Product.getSomeProducts();
        Population population = new Population(20, products, 3.0);
        population.run(500, 1);
    }
}