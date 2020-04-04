package com.br.ijrodrigues.introductiontogeneticalgorithms.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

@Getter
@ToString
public class Population {

    private Integer populationSize;
    private Individual bestIndividual;
    private Integer generation = null;
    private List<Individual> individuals = new ArrayList<>();

    public Population(Integer populationSize) {
        this.populationSize = populationSize;
    }

    public void initialize(List<Product> products, Double sizeLimit){
        generatePopulation(products, sizeLimit);
        sortIndividualsByFitness();
        this.bestIndividual = individuals.get(0);
    }

    public Double getPopulationFitness(){
        return this.individuals.stream()
                .map(Individual::getFitness)
                .reduce((accumulator, individualFitnees) -> accumulator += individualFitnees)
                .orElse(0.0);
    }

    private void generatePopulation(List<Product> products, Double sizeLimit) {
        IntStream.range(0, populationSize).forEach(value -> individuals.add(new Individual(products, sizeLimit)));
    }

    private void sortIndividualsByFitness(){
        this.individuals.sort(comparing(Individual::getFitness).reversed());
    }
}