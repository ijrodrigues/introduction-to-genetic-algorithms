package com.br.ijrodrigues.introductiontogeneticalgorithms;

import com.br.ijrodrigues.introductiontogeneticalgorithms.domain.Individual;
import com.br.ijrodrigues.introductiontogeneticalgorithms.domain.Population;
import com.br.ijrodrigues.introductiontogeneticalgorithms.domain.Product;
import com.br.ijrodrigues.introductiontogeneticalgorithms.infrastructure.Graphic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootApplication
public class IntroductionToGeneticAlgorithmsApplication {

    public static void main(String[] args) {
//        SpringApplication.run(IntroductionToGeneticAlgorithmsApplication.class, args);

        ArrayList<Product> products = Product.getSomeProducts();
        Population population = new Population(20, products, 3.0);
        population.run(500, 1);
        population.displayResults();
    }
}