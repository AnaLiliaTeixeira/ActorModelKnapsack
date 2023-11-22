package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class MeasureFitnessMessage extends Message {

    private Individual ind;
    private int generation;

    public MeasureFitnessMessage(Individual ind, int generation) {
        this.ind = ind;
        this.generation = generation;
    }

    public Individual getIndividual() {
        return this.ind;
    }

    public int getGeneration() {
        return generation;
    }
}
