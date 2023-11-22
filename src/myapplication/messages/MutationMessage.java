package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class MutationMessage extends Message {

    private Individual ind;
    private Individual best;

    public MutationMessage(Individual ind, Individual best) {
        this.ind = ind;
        this.best = best;
    }

    public Individual getIndividual() {
        return this.ind;
    }

    public Individual getBest() {
        return best;
    }
}
