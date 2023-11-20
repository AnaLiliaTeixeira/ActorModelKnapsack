package myapplication.actors;

import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.CalculateBestIndividualMessage;
import myapplication.messages.ResponseBestIndividualMessage;

public class BestIndividualActor extends Actor {

    private Individual[] population;

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
            this.send(new ResponseBestIndividualMessage(best, population), cm.getSenderAddress());
        }
    }
    
}
