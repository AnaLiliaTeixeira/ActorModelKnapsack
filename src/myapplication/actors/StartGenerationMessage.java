package myapplication.actors;

import library.messages.Message;

public class StartGenerationMessage extends Message {

    private Individual[] population;

    public StartGenerationMessage(Individual[] population) {
        this.population = population;
        // this.generation = generation;
    }

    // public int getGeneration() {
    //     return generation;
    // }

    public Individual[] getPopulation() {
        return population;
    }

}
