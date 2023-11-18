package myapplication.actors;

import java.util.Random;

import library.Actor;
import library.messages.Message;
import library.messages.SystemKillMessage;
// import myapplication.messages.CalculateFitnessMessage;
import myapplication.MeasureFitnessActor;

public class KnapsackActor extends Actor {

    private static final int N_GENERATIONS = 500;
	private static final int POP_SIZE = 100000;
	private static final double PROB_MUTATION = 0.5;
	private static final int TOURNAMENT_SIZE = 3;

	private Random r = new Random();

	// private Individual[] population = new Individual[POP_SIZE];

    private int currentGeneration = 0;
    private MeasureFitnessActor fitnessActor = new MeasureFitnessActor();
    private CrossoverActor crossoverActor = new CrossoverActor();
    private MutationActor mutationActor = new MutationActor();
    private Actor[] actors = {this, fitnessActor, crossoverActor, mutationActor};

	public KnapsackActor() {
		// populateInitialPopulationRandomly();
	}

	private void populateInitialPopulationRandomly() {
		/* Creates a new population, made of random Individuals */

        ///////////////////Fazer isto como actor? ////////////////////////////
		// for (int i = 0; i < POP_SIZE; i++) {
		// 	population[i] = Individual.createRandom(r);
		// }
        /////////////////////////////////////////////
	}

	// private Individual tournament(int tournamentSize, Random r, Individual[] population) {
	// 	/*
	// 	 * In each tournament, we select tournamentSize Individuals at random, and we
	// 	 * keep the best of those.
	// 	 */
	// 	Individual best = population[r.nextInt(POP_SIZE)];
	// 	for (int i = 0; i < tournamentSize; i++) {
	// 		Individual other = population[r.nextInt(POP_SIZE)];
	// 		if (other.fitness > best.fitness) {
	// 			best = other;
	// 		}
	// 	}
	// 	return best;
	// }

	private Individual bestOfPopulation(Individual[] population) {
		/*
		 * Returns the best Individual of the population.
		 */
		Individual best = population[0];
		for (Individual other : population) {
			if (other.fitness > best.fitness) {
				best = other;
			}
		}
		return best;
	}

    @Override
    protected void handleMessage(Message m) {
            // Individual[] newPopulation = new Individual[POP_SIZE];
            if (m instanceof StartGenerationMessage sm) {
                // System.out.println("Starting knapsack actor generation: "+ currentGeneration);
                // Step1 - Calculate Fitness
                this.send(new MeasureFitnessMessage(POP_SIZE, sm.getPopulation(), currentGeneration), fitnessActor.getAddress());
            }
            else if (m instanceof FitnessMeasuredMessage fm) {
                // Step2 - Print the best Individual so far.
                Individual best = bestOfPopulation(fm.getPopulation());
                System.out.println("Best at generation " + currentGeneration + " is " + best + " with "
                        + best.fitness);
                        // else if (m instanceof ) {
                // Step3 - Find parents to mate (cross-over)    
                this.send(new CrossoverMessage(POP_SIZE, TOURNAMENT_SIZE, fm.getPopulation(), best), crossoverActor.getAddress());

                // this.send(new MutationMessage(POP_SIZE, newPopulation, PROB_MUTATION), this.getAddress());
                // System.out.println("finished tournament");
            } 
            // else if (m instanceof CrossoverDoneMessage cm) {
            //     this.send(new MutationMessage(POP_SIZE, newPopulation, PROB_MUTATION), this.getAddress());
            // }
            // }
            else if (m instanceof MutationMessage mm) {
                // System.out.println("Started mutation");
                // Step4 - Mutate
                this.send(new MutationMessage(POP_SIZE, mm.getPopulation(), PROB_MUTATION), mutationActor.getAddress());
                // System.out.println("ended mutation");
            }
            else if (m instanceof GenerationCompletedMessage dm) {
                // System.out.println("Stared done message");
                // population = dm.getPopulation();
                currentGeneration++;
                // System.out.println(currentGeneration);
                if (currentGeneration == N_GENERATIONS) {
                    for (Actor actor : actors) {
                        this.send(new SystemKillMessage(), actor.getAddress());
                    }
                    this.send(new SystemKillMessage(), m.getSenderAddress());
                }
                else {
                    this.send(new StartGenerationMessage(dm.getPopulation()), this.getAddress());
                }
                // System.out.println("Ended done message");
            }
          
    }
    
}
