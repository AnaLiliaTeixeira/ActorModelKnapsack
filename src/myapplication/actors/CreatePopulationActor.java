package myapplication.actors;

import java.util.concurrent.ThreadLocalRandom;

import library.Actor;
import library.Individual;
import library.messages.Message;
import library.messages.SystemKillMessage;
import myapplication.messages.CreatePopulationMessage;
import myapplication.messages.StartGenerationMessage;

public class CreatePopulationActor extends Actor {

    // private Actor ka = launchActor(new KnapsackActor());
    private Individual[] population;
    private ThreadLocalRandom r = ThreadLocalRandom.current();
    
    private boolean endProgram = false;
    private long endExecutionTime;

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof CreatePopulationMessage cm) {
            this.population = new Individual[cm.getPopSize()];
            for (int i = 0; i < cm.getPopSize(); i++) {
                population[i] = Individual.createRandom(r);
            }
            this.send(new StartGenerationMessage(this.population), m.getSenderAddress());
        } else if (m instanceof SystemKillMessage) {
            System.out.println("final kill");
            endProgram = true;
            this.setEndExecutionTime(System.nanoTime());

            synchronized (this) {
                this.notifyAll();
            }
        }
    }

    public long waitForActorToFinish() throws InterruptedException {
        // Aguarde até que a conclusão seja sinalizada pelo ator
        synchronized (this) {
            while (!endProgram) {
                this.wait();
            }
        }
        // try {
        //     ka.join();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        System.out.println("returning execution time =" + this.endExecutionTime);
        return this.endExecutionTime;
    }

    public void setEndExecutionTime(long time) {
        this.endExecutionTime = time;
    }

    public long getEndExecutionTime() {
        return this.endExecutionTime;
    }
}
