package myapplication.messages;

import library.Individual;
import library.messages.Message;

public class StartGenerationMessage extends Message {

    private Individual ind;

    public StartGenerationMessage(Individual ind) {
        this.ind = ind;
    }

    public Individual getIndividual() {
        return ind;
    }

}
