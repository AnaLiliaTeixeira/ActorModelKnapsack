package myapplication.messages;

import library.messages.Message;

public class CreatePopulationMessage extends Message {

    private int individualId;

    public CreatePopulationMessage(int individualId) {
        super();
        this.individualId = individualId;
    }

    public void setIndividualId(int individualId) {
        this.individualId = individualId;
    }

    public int getIndividualId() {
        return individualId;
    }
}
