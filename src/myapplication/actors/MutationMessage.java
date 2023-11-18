package myapplication.actors;

import library.messages.Message;

public class MutationMessage extends Message {

    private int pop_size;
    private Individual[] population;
    private double prob_mutation;

    public MutationMessage(int pop_size, Individual[] population, double prob_mutation) {
        this.pop_size = pop_size;
        this.population = population;
        this.prob_mutation = prob_mutation;
    }
    
    public int getPopSize() {
        return this.pop_size;
    }

    public Individual[] getPopulation() {
        return this.population;
    }
    public double getProbMutation() {
        return this.prob_mutation;
    }

}
