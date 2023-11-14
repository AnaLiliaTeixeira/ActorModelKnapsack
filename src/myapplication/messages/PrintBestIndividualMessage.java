package myapplication.messages;

import library.messages.Message;
import myapplication.IndividualActor;

public class PrintBestIndividualMessage extends Message {
    
    private IndividualActor best;
    
    public PrintBestIndividualMessage(IndividualActor best) {
        super();
        this.best = best;
    }

    public IndividualActor getBestIndividual() {
        return best;
    }
}
