package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class GenerationCompletedMessage extends Message {

    private Individual[] population;

    public GenerationCompletedMessage(Individual[] population) {
        this.population = population;
    }

    public Individual[] getPopulation() {
        return population;
    }

}
