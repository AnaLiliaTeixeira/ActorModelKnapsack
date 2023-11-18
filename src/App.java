import library.Actor;
import myapplication.actors.CreatePopulationActor;
import myapplication.actors.CreatePopulationMessage;

public class App {
    private static final int POP_SIZE = 100000;

    public static void main(String[] args) throws Exception {

        long start = System.nanoTime();
        CreatePopulationActor ca = new CreatePopulationActor();
        Actor.sendFromMain(new CreatePopulationMessage(POP_SIZE), ca.getAddress());

        long end = System.nanoTime();
        System.out.println("Time token: " + (end-start));
    }
}
