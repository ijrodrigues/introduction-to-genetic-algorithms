package com.br.ijrodrigues.introductiontogeneticalgorithms.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Slf4j
@Getter
public class Individual {
    private Integer generation;
    private Double sizeLimit;
    private Double fitness = 0.0;
    private Double usedSpace = 0.0;
    private List<Product> products;
    private List<Boolean> genes = new ArrayList<>();

    public Individual(List<Product> products, Double sizeLimit) {
        this(products, sizeLimit, 0);
    }

    private Individual(List<Product> products, Double sizeLimit, Integer generation) {
        this.sizeLimit = sizeLimit;
        this.generation = generation;
        this.products = products;
        this.products.forEach(this::setInitialValues);
        calculateFitness();
    }

    private Individual(int generation, Double sizeLimit, List<Product> products, List<Boolean> genes) {
        this.generation = generation;
        this.sizeLimit = sizeLimit;
        this.products = products;
        this.genes = genes;
    }

    public void calculateFitness(){
        Double fitness = 0.0;
        Double usedSpace = 0.0;

        for(int x = 0; x < this.genes.size(); x++){
            if(this.genes.get(x)){
                fitness += this.products.get(x).getPrice();
                usedSpace += this.products.get(x).getSize();
            }
        }

        if (usedSpace > sizeLimit) {
            fitness = 1.0;
        }

        this.fitness = fitness;
        this.usedSpace = usedSpace;
    }

    public List<Individual> crossover(Individual other) {
        int cutPath = (int) Math.round(Math.random() * this.genes.size());
        log.info("Cortado em: " + cutPath);
        return asList(
                generateNewIndividual(this, other, cutPath),
                generateNewIndividual(other, this, cutPath));
    }

    public Individual mutate(Integer probability) {
        log.info("genes antes da mutação " + this.genes.toString());

        for (int x = 0; x < this.genes.size(); x++) {
            if (Math.random() < (probability.doubleValue() / 100)) {
                if (this.genes.get(x).equals(true)) {
                    this.genes.set(x, false);
                } else {
                    this.genes.set(x, true);
                }
            }
        }

        log.info("genes depois da mutação " + this.genes.toString());

        return this;
    }

    private void setInitialValues(Product product) {
        boolean selectedItem = shouldSelectItem();
        genes.add(selectedItem);
    }

    private boolean shouldSelectItem() {
        return Math.random() < 0.5;
    }

    private Individual generateNewIndividual(Individual firstParent, Individual secondParent, int cutPath) {
        Individual individual = new Individual(
                this.generation + 1,
                this.sizeLimit,
                this.products,
                generateNewGenes(firstParent, secondParent, cutPath));
        individual.calculateFitness();
        return individual;
    }

    private List<Boolean> generateNewGenes(Individual firstParent, Individual secondParent, int cutPath) {
        List<Boolean> newGenes = new ArrayList<>(this.genes.size());
        newGenes.addAll(firstParent.getGenes().subList(0, cutPath));
        newGenes.addAll(secondParent.genes.subList(cutPath, this.genes.size()));
        return newGenes;
    }

    @Override
    public String toString() {
        return "Individual{" +
                ", genes=" + genes +
                ", sizeLimit=" + sizeLimit +
                ", usedSpace=" + usedSpace +
                ", fitness=" + fitness +
                ", generation=" + generation +
                "}\n\n";
    }
}