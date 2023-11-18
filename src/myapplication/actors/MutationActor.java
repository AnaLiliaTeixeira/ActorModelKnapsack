package myapplication.actors;

import java.util.Random;

import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.GenerationCompletedMessage;
import myapplication.messages.MutationMessage;

public class MutationActor extends Actor {

    private Individual[] population;
    private Random r = new Random();

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof MutationMessage mm) {
            population = mm.getPopulation();

            for (int i = 1; i < mm.getPopSize(); i++) {
                if (r.nextDouble() < mm.getProbMutation()) {
                    population[i].mutate(r);
                }
            }
            this.send(new GenerationCompletedMessage(population), m.getSenderAddress());
        }
    }

}
