import library.Actor;
import myapplication.GenerationsActor;
import myapplication.messages.StartMessage;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        GenerationsActor ga = new GenerationsActor();
        Actor.sendFromMain(new StartMessage(), ga.getAddress());
    }
}
