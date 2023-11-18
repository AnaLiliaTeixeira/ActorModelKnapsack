package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class MeasureFitnessMessage extends Message {

    private int pop_size;
    private Individual[] population;
    private int generation;

    public MeasureFitnessMessage(int pop_size, Individual[] population, int generation) {
        this.pop_size = pop_size;
        this.population = population;
        this.generation = generation;
    }

    public int getPopSize() {
        return this.pop_size;
    }

    public Individual[] getPopulation() {
        return this.population;
    }

    public int getGeneration() {
        return generation;
    }
}
