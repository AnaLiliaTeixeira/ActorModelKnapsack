package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class StartGenerationMessage extends Message {

    private Individual[] population;

    public StartGenerationMessage(Individual[] population) {
        this.population = population;
    }

    public Individual[] getPopulation() {
        return population;
    }

}
