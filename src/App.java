import library.Actor;
import myapplication.KnapsackActor;
import myapplication.messages.StartMessage;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        KnapsackActor ka = new KnapsackActor();
        Actor.sendFromMain(new StartMessage(), ka.getAddress());
    }
}
