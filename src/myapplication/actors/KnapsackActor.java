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

    private Actor mutationActor = new MutationActor();
    private Actor crossoverActor = new CrossoverActor();
    private Actor bestActor = new BestIndividualActor();
    private Actor fitnessActor = new MeasureFitnessActor();
    private Actor createPopulationActor = new CreatePopulationActor();

    public KnapsackActor() {
        this.launchActor(createPopulationActor);
        createPopulationActor.launchActor(fitnessActor);
        fitnessActor.launchActor(bestActor);
        bestActor.launchActor(crossoverActor);
        crossoverActor.launchActor(mutationActor);
        mutationActor.launchActor(this);
    }
    @Override
    protected void handleMessage(Message m) {
        if (m instanceof CreatePopulationMessage cm) {
            this.send(cm, createPopulationActor.getAddress());
        }
        // else if (m instanceof StartGenerationMessage sm) {
        //     // Step1 - Calculate Fitness
        //     this.send(new MeasureFitnessMessage(sm.getIndividual(), currentGeneration), fitnessActor.getAddress());
        // }
        else if (m instanceof GenerationCompletedMessage dm) {
            currentGeneration++;
            if (currentGeneration == N_GENERATIONS) {
                this.send(new SystemKillMessage(), this.getAddress());
            }
            else {
                for (int i = 0; i < POP_SIZE; i++) {
                    this.send(new MeasureFitnessMessage(dm.getPopulation()[i], currentGeneration), fitnessActor.getAddress());

                    // this.send(new StartGenerationMessage(dm.getPopulation()[i]), this.getAddress());
                }
            }
        }
    }
}
