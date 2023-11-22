package myapplication.messages;
import library.messages.Message;

public class CreatePopulationMessage extends Message {
    
    private int generation;

    public CreatePopulationMessage(int generation) {
        this.generation = generation;
    }

    public int getGeneration() {
        return generation;
    }
}
