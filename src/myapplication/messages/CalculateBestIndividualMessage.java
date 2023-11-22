package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class CalculateBestIndividualMessage extends Message {

    private Individual[] population;
    private int currentGeneration;

    public CalculateBestIndividualMessage(Individual[] population, int currentGeneration) {
        this.population = population;
        this.currentGeneration = currentGeneration;
    }
    public Individual[] getPopulation() {
        return this.population;
    }

    public int getGeneration() {
        return this.currentGeneration;
    }
}
