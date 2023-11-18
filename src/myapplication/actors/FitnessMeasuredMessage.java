package myapplication.actors;
import library.Individual;
import library.messages.Message;

public class FitnessMeasuredMessage extends Message {
    private Individual[] population;
    private int generation;

    public FitnessMeasuredMessage(Individual[] population, int generation) {
        this.population = population;
        this.generation = generation;
    }

    public Individual[] getPopulation() {
        return this.population;
    }

    public void setPopulation(Individual[] population) {
        this.population = population;
    }

    public int getGeneration() {
        return generation;
    }
}
