package myapplication.messages;

import library.messages.Message;
import myapplication.IndividualActor;

public class CreatePopulationMessage extends Message {

    
    private int individualId;
    private IndividualActor individual;

    public CreatePopulationMessage(int individualId, IndividualActor individual) {
        super();
        this.individualId = individualId;
        this.individual = individual;
    }

    public void setIndividualId(int individualId) {
        this.individualId = individualId;
    }

    public int getIndividualId() {
        return individualId;
    }
    
    public void setIndividual(IndividualActor individual) {
        this.individual = individual;
    }

    public IndividualActor getIndividual() {
        return individual;
    }
}
