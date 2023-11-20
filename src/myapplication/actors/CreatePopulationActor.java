package myapplication.actors;

import java.util.concurrent.ThreadLocalRandom;

import library.Actor;
import library.Individual;
import library.messages.Message;
import library.messages.SystemKillMessage;
import myapplication.messages.CreatePopulationMessage;
import myapplication.messages.StartGenerationMessage;

public class CreatePopulationActor extends Actor {

    private Actor ka = launchActor(new KnapsackActor());
    private Individual[] population;
    private ThreadLocalRandom r = ThreadLocalRandom.current();

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof CreatePopulationMessage cm) {
            this.population = new Individual[cm.getPopSize()];
            for (int i = 0; i < cm.getPopSize(); i++) {
                population[i] = Individual.createRandom(r);
            }
            this.send(new StartGenerationMessage(this.population), ka.getAddress());
        }
        else if (m instanceof SystemKillMessage) {
            System.out.println("HEREEE");
            this.send(new SystemKillMessage(), this.getAddress());
            // this.send(new SystemKillMessage(), ka.getAddress());
        }
    }
    
}
