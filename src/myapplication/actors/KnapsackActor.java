package myapplication.actors;

import library.Actor;
import library.Individual;
import library.messages.Message;
import library.messages.SystemKillMessage;
import myapplication.messages.CalculateBestIndividualMessage;
import myapplication.messages.CreatePopulationMessage;
import myapplication.messages.CrossoverMessage;
import myapplication.messages.FitnessMeasuredMessage;
import myapplication.messages.GenerationCompletedMessage;
import myapplication.messages.MeasureFitnessMessage;
import myapplication.messages.MutationMessage;
import myapplication.messages.ResponseBestIndividualMessage;
import myapplication.messages.StartGenerationMessage;

public class KnapsackActor extends Actor {

    private static final int N_GENERATIONS = 500;
    private static final int POP_SIZE = 100000;

    private int currentGeneration = 0;
    private Actor createPopulationActor = launchActor(new CreatePopulationActor());
    private Actor fitnessActor = launchActor(new MeasureFitnessActor());
    private Actor bestActor = launchActor(new BestIndividualActor());
    private Actor crossoverActor = launchActor(new CrossoverActor());
    private Actor mutationActor = launchActor(new MutationActor());

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof CreatePopulationMessage cm) {
            this.send(cm, createPopulationActor.getAddress());
        }
        else if (m instanceof StartGenerationMessage sm) {
            // Step1 - Calculate Fitness
            this.send(new MeasureFitnessMessage(sm.getIndividual(), currentGeneration), fitnessActor.getAddress());
        }
        else if (m instanceof FitnessMeasuredMessage fm) {
            this.send(new CalculateBestIndividualMessage(fm.getPopulation()), bestActor.getAddress());
        }
        else if (m instanceof ResponseBestIndividualMessage rm) {
            // Step2 - Print the best Individual so far.
            Individual best = rm.getBest();
            System.out.println("Best at generation " + currentGeneration + " is " + best + " with "
                    + best.fitness);

            // Step3 - Find parents to mate (cross-over)  
            for (int i = 1; i < POP_SIZE; i++) {
                this.send(new CrossoverMessage(rm.getPopulation(), rm.getBest()), crossoverActor.getAddress());
            }
        }
        else if (m instanceof MutationMessage mm) {
            // Step4 - Mutate
            this.send(new MutationMessage(mm.getIndividual(), mm.getBest()), mutationActor.getAddress());
        }
        else if (m instanceof GenerationCompletedMessage dm) {
            currentGeneration++;
            if (currentGeneration == N_GENERATIONS) {
                this.send(new SystemKillMessage(), this.getAddress());
            }
            else {
                for (int i = 0; i < POP_SIZE; i++) {
                    this.send(new StartGenerationMessage(dm.getPopulation()[i]), this.getAddress());
                }
            }
        }
    }
}
