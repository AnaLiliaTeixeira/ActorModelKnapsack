package myapplication.actors;

import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.CalculateBestIndividualMessage;
import myapplication.messages.CrossoverMessage;

public class BestIndividualActor extends Actor {

    private Individual[] population;
    private static final int POP_SIZE = 100000;

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof CalculateBestIndividualMessage cm) {
            population = cm.getPopulation();
            Individual best = population[0];
            for (Individual other : population) {
                if (other.fitness > best.fitness) {
                    best = other;
                }
            }
            // Step2 - Print the best Individual so far.
            System.out.println("Best at generation " + cm.getGeneration() + " is " + best + " with "
                    + best.fitness);

            // Step3 - Find parents to mate (cross-over) 
            for (int i = 1; i < POP_SIZE; i++) {
                this.send(new CrossoverMessage(population, best), this.getChild().getAddress());
            }
        }
    }
}
