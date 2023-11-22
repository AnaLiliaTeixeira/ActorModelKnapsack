import java.io.FileWriter;
import java.io.IOException;

import library.Actor;
import library.messages.SystemKillMessage;
import myapplication.actors.BestIndividualActor;
import myapplication.actors.CreatePopulationActor;
import myapplication.actors.CrossoverActor;
import myapplication.actors.KnapsackActor;
import myapplication.actors.MeasureFitnessActor;
import myapplication.actors.MutationActor;
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
                System.out.println(actorTime);
                csvWriter.append(actorTime + "\n");
                
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long runKnapsackActorModel() {

            long start = System.nanoTime();
            Actor knapsackActor = new KnapsackActor();
            Actor createPopulationActor = new CreatePopulationActor();
            Actor fitnessActor = new MeasureFitnessActor();
            Actor bestActor = new BestIndividualActor();
            Actor crossoverActor = new CrossoverActor();
            Actor mutationActor = new MutationActor();

            knapsackActor.launchActor(createPopulationActor);
            createPopulationActor.launchActor(fitnessActor);
            fitnessActor.launchActor(bestActor);
            bestActor.launchActor(crossoverActor);
            crossoverActor.launchActor(mutationActor);
            mutationActor.launchActor(knapsackActor);

            Actor.sendFromMain(new CreatePopulationMessage(), knapsackActor.getAddress());
            
            try {
                knapsackActor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Actor.sendFromMain(new SystemKillMessage(), knapsackActor.getAddress());
            return System.nanoTime() - start;
    }
}
