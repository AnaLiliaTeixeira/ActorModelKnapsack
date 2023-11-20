package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class CalculateBestIndividualMessage extends Message {

    private Individual[] population;

    public CalculateBestIndividualMessage(Individual[] population) {
        this.population = population;
    }
    public Individual[] getPopulation() {
        return this.population;
    }

}
