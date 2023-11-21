package myapplication.actors;

import java.util.concurrent.ThreadLocalRandom;

import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.GenerationCompletedMessage;
import myapplication.messages.MutationMessage;

public class MutationActor extends Actor {

    private Individual[] population;
    private static final int POP_SIZE = 100000;
    private static final double PROB_MUTATION = 0.5;
    private ThreadLocalRandom r = ThreadLocalRandom.current();

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof MutationMessage mm) {
            population = mm.getPopulation();

            for (int i = 1; i < POP_SIZE; i++) {
                if (r.nextDouble() < PROB_MUTATION) {
                    population[i].mutate(r);
                }
            }
            this.send(new GenerationCompletedMessage(population), m.getSenderAddress());
        }
    }
}
