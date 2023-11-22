package myapplication.actors;

import library.Actor;
import library.messages.Message;
import library.messages.SystemKillMessage;
import myapplication.messages.CreatePopulationMessage;
import myapplication.messages.GenerationCompletedMessage;
import myapplication.messages.MeasureFitnessMessage;

public class KnapsackActor extends Actor {

    private static final int N_GENERATIONS = 500;
    private static final int POP_SIZE = 100000;
    private int currentGeneration = 0;

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof CreatePopulationMessage cm) {
            cm.setGeneration(currentGeneration);
            this.send(cm, this.getChild().getAddress());
        }
        else if (m instanceof GenerationCompletedMessage dm) {
            currentGeneration++;
            if (currentGeneration == N_GENERATIONS) {
                this.send(new SystemKillMessage(), this.getAddress());
            }
            else {
                for (int i = 0; i < POP_SIZE; i++) {
                    this.send(new MeasureFitnessMessage(dm.getPopulation()[i], currentGeneration), this.getChild().getChild().getAddress());
                }
            }
        }
    }
}
