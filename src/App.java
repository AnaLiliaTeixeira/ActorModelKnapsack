import java.util.concurrent.Phaser;

import library.Actor;
import library.messages.SystemKillMessage;
import myapplication.actors.CreatePopulationActor;
import myapplication.actors.KnapsackActor;
import myapplication.messages.CreatePopulationMessage;
import java.util.concurrent.Phaser;

public class App {
    private static final int POP_SIZE = 100000;
    private static final int ITERATIONS = 2;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < ITERATIONS; i++) {
            System.out.println("Iteration: " + i);

            long start = System.nanoTime();
            KnapsackActor ka = new KnapsackActor();
            Actor.sendFromMain(new CreatePopulationMessage(POP_SIZE), ka.getAddress());

            try {
                ka.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Actor.sendFromMain(new SystemKillMessage(), ka.getAddress());

            // long end = ka.waitForActorToFinish();
            // System.out.println("Time token: " + (end - start) + "\n");
        }
    }
}
