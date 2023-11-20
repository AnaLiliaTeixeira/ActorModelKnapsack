package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class ResponseBestIndividualMessage extends Message {

    private Individual best;
    private Individual[] population;

    public ResponseBestIndividualMessage(Individual best, Individual[] population) {
        this.best = best;
        this.population = population;
    }

    public Individual getBest() {
        return this.best;
    }

    public Individual[] getPopulation() {
        return this.population;
    }

}
