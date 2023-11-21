import java.io.FileWriter;
import java.io.IOException;

import library.Actor;
import library.messages.SystemKillMessage;
import myapplication.actors.KnapsackActor;
import myapplication.messages.CreatePopulationMessage;

public class App {
    private static final int ITERATIONS = 30;

    public static void main(String[] args) throws InterruptedException {

        try {
            FileWriter csvWriter = new FileWriter("resultsActorModel.csv");
            csvWriter.append("Actor Model\n");
            for (int i = 0; i < ITERATIONS; i++) {

                System.out.println("\nIteration: " + i);
                long actorTime = runKnapsackActorModel();

                csvWriter.append(actorTime + "\n");
                
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long runKnapsackActorModel() {

            long start = System.nanoTime();
            KnapsackActor ka = new KnapsackActor();
            Actor.sendFromMain(new CreatePopulationMessage(), ka.getAddress());
            
            try {
                ka.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Actor.sendFromMain(new SystemKillMessage(), ka.getAddress());
            return System.nanoTime() - start;
    }
}
