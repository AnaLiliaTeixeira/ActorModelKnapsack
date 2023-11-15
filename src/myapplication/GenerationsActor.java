package myapplication;

import library.Actor;
import library.messages.Message;
import library.messages.SystemKillMessage;
import myapplication.messages.PrintBestIndividualMessage;
import myapplication.messages.ResponseMessage;
import myapplication.messages.StartMessage;

public class GenerationsActor extends Actor {

    private static final int N_GENERATIONS = 500;
    private int responsesReceived = 0;

    public GenerationsActor() {
    }

    @Override
    protected void handleMessage(Message m) {
        // falta fazer o system kill quando todas as gerações forem percorridas
        if (m instanceof StartMessage sm) {

            for (int generation = 0; generation < N_GENERATIONS; generation++) {
                System.out.println("Starting generation " + generation);
                KnapsackActor ka = new KnapsackActor();
                this.send(new StartMessage(), ka.getAddress());

                if (m instanceof PrintBestIndividualMessage pbm) {
                    System.out.println("Best at generation " + generation + " is " + pbm.getBestIndividual() + " with "
                    + pbm.getBestIndividual().fitness);
                }
                else if (m instanceof ResponseMessage rm) {
                    System.out.println("Generation " + generation + " completed.");
                    System.out.println();

                    if (responsesReceived == N_GENERATIONS) {
                        this.send(new SystemKillMessage(), this.getAddress());
                    }
                }
            }
        }
    }
    
}
