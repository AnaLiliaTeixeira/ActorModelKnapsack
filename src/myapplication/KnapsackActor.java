package myapplication;

import library.Actor;
import library.messages.Message;
import library.messages.SystemKillMessage;
import myapplication.messages.ResponseMessage;
import myapplication.messages.StartMessage;

public class KnapsackActor extends Actor {

    //aqui tenho de fazer launch ao individual actor
    // o individual vai receber as mensagens do knapsack e vai enviar para o knapsack um responde message
    private int responsesReceived = 0;

    @Override
    protected void handleMessage(Message m) {
        
        if (m instanceof StartMessage sm) {
            System.out.println("StartMessage received");
            this.send(new ResponseMessage(), this.getAddress());
            // knapsack envia a individual msg fitness, best individual, tournament, mutation
            // isto é como se fosse a main
        }
        else if (m instanceof ResponseMessage rm) {
            System.out.println("ResultMessage received");
            responsesReceived++;
            // 4 msg recebidas -> fitness, best individual, tournament, mutation
            if (responsesReceived == 1) {
                System.out.println("All responses received");
                this.send(new SystemKillMessage(), this.getAddress());
            }
        }
        else {
            System.out.println("Unknown message received");
        }
    }
    
}
