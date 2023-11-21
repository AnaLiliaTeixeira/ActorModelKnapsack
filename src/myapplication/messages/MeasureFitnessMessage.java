package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class MeasureFitnessMessage extends Message {

    private Individual[] population;
    private int generation;

    public MeasureFitnessMessage(Individual[] population, int generation) {
        this.population = population;
        this.generation = generation;
    }

    public Individual[] getPopulation() {
        return this.population;
    }

    public int getGeneration() {
        return generation;
    }
}
