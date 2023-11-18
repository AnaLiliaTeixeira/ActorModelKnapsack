package myapplication.actors;

import java.util.Random;

import library.Actor;
import library.messages.Message;
import library.messages.SystemKillMessage;

public class CreatePopulationActor extends Actor {

    private KnapsackActor ka = new KnapsackActor();
    private Individual[] population;
    private Random r = new Random();

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
            this.send(new SystemKillMessage(), ka.getAddress());
        }
    }
    
}
