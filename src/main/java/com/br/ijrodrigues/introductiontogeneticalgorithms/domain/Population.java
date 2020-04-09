package com.br.ijrodrigues.introductiontogeneticalgorithms.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

@Getter
public class Population {

    private Integer populationSize;
    private Integer generation;
    private Individual bestIndividualEver;
    private List<Individual> individuals = new ArrayList<>();

    public Population(Integer populationSize, List<Product> products, Double sizeLimit) {
        this.populationSize = populationSize;

        generatePopulation(products, sizeLimit);
        sortIndividualsByFitness();
        this.bestIndividualEver = individuals.get(0);
        this.generation = 0;
        printPopulationDetails();
    }

    public void run(int generations, int mutationProbability) {
        IntStream.range(0, generations).forEach(value -> newGeneration(mutationProbability));
        System.out.println("\n\nBest solution found = " + bestIndividualEver);
    }

    private void newGeneration(Integer mutationProbability) {
        List<Individual> newIndividuals = new ArrayList<>();

        IntStream.range(0, populationSize / 2)
                .forEach(value -> {
                    List<Individual> children = selectParent().crossover(selectParent());

                    Individual firstChild = children.get(0).mutate(mutationProbability);
                    firstChild.calculateFitness();

                    Individual secondChild = children.get(1).mutate(mutationProbability);
                    secondChild.calculateFitness();

                    newIndividuals.add(firstChild);
                    newIndividuals.add(secondChild);
                });

        this.individuals = newIndividuals;
        sortIndividualsByFitness();
        this.generation += 1;

        if (this.individuals.get(0).getFitness() > this.bestIndividualEver.getFitness()) {
            this.bestIndividualEver = individuals.get(0);
        }

        printPopulationDetails();
    }

    private void printPopulationDetails() {
        Individual bestIndividual = individuals.get(0);

        System.out.println(
                "G = " + generation +
                        ", Best Result = " + bestIndividual.getFitness() +
                        ", Used Space = " + bestIndividualEver.getUsedSpace() +
                        ", Genes = " + bestIndividual.getGenes()
        );
    }

    private Double getPopulationFitness() {
        return this.individuals.stream()
                .map(Individual::getFitness)
                .reduce((accumulator, individualFitnees) -> accumulator += individualFitnees)
                .orElse(0.0);
    }

    private Individual selectParent() {
        Double sortedValue = Math.random() * getPopulationFitness();
        Double sum = 0.0;
        int position = 0;

        while (position < individuals.size() && sum < sortedValue) {
            sum += individuals.get(position).getFitness();
            position += 1;
        }
        return individuals.get(position - 1);
    }

    private void generatePopulation(List<Product> products, Double sizeLimit) {
        IntStream.range(0, populationSize).forEach(value -> individuals.add(new Individual(products, sizeLimit)));
    }

    private void sortIndividualsByFitness() {
        this.individuals.sort(comparing(Individual::getFitness).reversed());
    }

    @Override
    public String toString() {
        return "Population{" +
                "populationSize=" + individuals.size() +
                ", generation=" + generation +
                ", bestIndividualOfTheRound=" + individuals.get(0).getFitness() +
                ", bestIndividualEver=" + bestIndividualEver.getFitness() +
                ", individuals=" + individuals +
                "}\n\n";
    }
}