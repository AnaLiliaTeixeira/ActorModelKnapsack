package myapplication.actors;
import library.messages.Message;

public class CreatePopulationMessage extends Message {

    private int pop_size;

    public CreatePopulationMessage(int pop_size) {
        this.pop_size = pop_size;
    }

    public int getPopSize() {
        return this.pop_size;
    }

}
