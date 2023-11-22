package myapplication.actors;

import java.util.concurrent.ThreadLocalRandom;

import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.CreatePopulationMessage;
import myapplication.messages.StartGenerationMessage;

public class CreatePopulationActor extends Actor {

    private Individual[] population = new Individual[POP_SIZE];
    private ThreadLocalRandom r = ThreadLocalRandom.current();
	private static final int POP_SIZE = 100000;
    
    @Override
    protected void handleMessage(Message m) {
        if (m instanceof CreatePopulationMessage cm) {
            for (int i = 0; i < POP_SIZE; i++) {
                population[i] = Individual.createRandom(r);
                this.send(new StartGenerationMessage(population[i]), m.getSenderAddress());
            }
        } 
    }
}
