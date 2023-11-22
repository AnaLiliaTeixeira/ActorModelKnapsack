package myapplication.messages;
import library.messages.Message;

public class CreatePopulationMessage extends Message {
    
    private int generation;

    public int getGeneration() {
        return generation;
    }
    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
