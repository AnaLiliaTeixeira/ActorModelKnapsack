package myapplication.actors;

import library.messages.Message;

public class CrossoverDoneMessage extends Message {

    private Individual[] population;

    public CrossoverDoneMessage(Individual[] population) {
        this.population = population;
    }

    public Individual[] getPopulation() {
        return this.population;
    }

}
