package myapplication.actors;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import library.Actor;
import library.Individual;
import library.messages.Message;
import library.messages.SystemKillMessage;
import myapplication.messages.CalculateBestIndividualMessage;
import myapplication.messages.CrossoverMessage;
import myapplication.messages.FitnessMeasuredMessage;
import myapplication.messages.GenerationCompletedMessage;
import myapplication.messages.MeasureFitnessMessage;
import myapplication.messages.MutationMessage;
import myapplication.messages.ResponseBestIndividualMessage;
import myapplication.messages.StartGenerationMessage;

public class KnapsackActor extends Actor {

    private static final int N_GENERATIONS = 5;
	private static final int POP_SIZE = 100000;
	private static final double PROB_MUTATION = 0.5;
	private static final int TOURNAMENT_SIZE = 3;

    private int currentGeneration = 0;
    private Actor fitnessActor = launchActor(new MeasureFitnessActor());
    private Actor bestActor = launchActor(new BestIndividualActor());
    private Actor crossoverActor = launchActor(new CrossoverActor());
    private Actor mutationActor = launchActor(new MutationActor());

    @Override
    protected void handleMessage(Message m) {
            if (m instanceof StartGenerationMessage sm) {
                // Step1 - Calculate Fitness
                System.out.println(this.getAddress());
                this.send(new MeasureFitnessMessage(POP_SIZE, sm.getPopulation(), currentGeneration), fitnessActor.getAddress());
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
                this.send(new CrossoverMessage(POP_SIZE, TOURNAMENT_SIZE, rm.getPopulation(), best), crossoverActor.getAddress());
            }
            else if (m instanceof MutationMessage mm) {
                // Step4 - Mutate
                this.send(new MutationMessage(POP_SIZE, mm.getPopulation(), PROB_MUTATION), mutationActor.getAddress());
            }
            else if (m instanceof GenerationCompletedMessage dm) {
                currentGeneration++;
                if (currentGeneration == N_GENERATIONS) {
                    this.send(new SystemKillMessage(), m.getSenderAddress());
                }
                else {
                    this.send(new StartGenerationMessage(dm.getPopulation()), this.getAddress());
                }
            }
    }
    
}
