package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class CrossoverMessage extends Message {

    private Individual[] population;
    private Individual best;

    public CrossoverMessage(Individual[] population, Individual best) {
        this.population = population;
        this.best = best;
    }

    public Individual[] getPopulation() {
        return this.population;
    }

    public Individual getBest() {
        return this.best;
    }
}
