import library.Actor;
import myapplication.GenerationsActor;
import myapplication.messages.StartMessage;

public class App {
    public static void main(String[] args) throws Exception {
        GenerationsActor ga = new GenerationsActor();
        Actor.sendFromMain(new StartMessage(), ga.getAddress());
        // ver quem é que decide quem é que faz o shutdown -> deve ser o generations actor qd receber 500 respostas do knapsack
    }
}
