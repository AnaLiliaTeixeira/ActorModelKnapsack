package myapplication.actors;

import java.util.concurrent.ThreadLocalRandom;

import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.GenerationCompletedMessage;
import myapplication.messages.MutationMessage;

public class MutationActor extends Actor {

    private static final int POP_SIZE = 100000;
    private Individual[] newPopulation = new Individual[POP_SIZE];
    private static final double PROB_MUTATION = 0.5;
    private ThreadLocalRandom r = ThreadLocalRandom.current();
    private int individualCounter = 0;

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof MutationMessage mm) {
            individualCounter++;
            if (r.nextDouble() < PROB_MUTATION) {
                mm.getIndividual().mutate(r);
            }
            newPopulation[individualCounter] = mm.getIndividual();
        
            if (individualCounter == POP_SIZE-1) {
                newPopulation[0] = mm.getBest(); // The best Individual remains
                this.send(new GenerationCompletedMessage(newPopulation), this.getChild().getAddress());
                individualCounter = 0;
            }
        }
    }
}
