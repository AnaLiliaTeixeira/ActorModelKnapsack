package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class MutationMessage extends Message {

    private Individual[] population;

    public MutationMessage(Individual[] population) {
        this.population = population;
    }

    public Individual[] getPopulation() {
        return this.population;
    }
}
