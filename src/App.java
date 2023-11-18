// import library.Actor;
// import myapplication.GenerationsActor;
// import myapplication.messages.StartMessage;

import library.Actor;
import myapplication.actors.CrossoverActor;
import myapplication.actors.CreatePopulationActor;
import myapplication.actors.CreatePopulationMessage;
import myapplication.actors.Individual;
import myapplication.actors.KnapsackActor;
import myapplication.actors.StartGenerationMessage;

public class App {
    // private static final int N_GENERATIONS = 5;
    private static final int POP_SIZE = 100000;

    public static void main(String[] args) throws Exception {

        long start = System.nanoTime();
        CreatePopulationActor ca = new CreatePopulationActor();
        // KnapsackActor ka = new KnapsackActor();
        // for (int generation = 0; generation < N_GENERATIONS; generation++) {
        Actor.sendFromMain(new CreatePopulationMessage(POP_SIZE), ca.getAddress());

        long end = System.nanoTime();
        System.out.println("Time token: " + (end-start));
        // Actor.sendFromMain(new StartGenerationMessage(new Individual[POP_SIZE]), ka.getAddress());
        // }
        // System.out.println("Here in app");
        // GenerationsActor ga = new GenerationsActor();
        // Actor.sendFromMain(new StartMessage(), ga.getAddress());
        // ver quem é que decide quem é que faz o shutdown -> deve ser o generations actor qd receber 500 respostas do knapsack
    }
}
