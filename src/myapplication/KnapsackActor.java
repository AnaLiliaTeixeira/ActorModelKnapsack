package myapplication;

import java.util.Random;

import library.Actor;
import library.messages.Message;
import library.messages.SystemKillMessage;
import myapplication.messages.CalculateFitnessMessage;
import myapplication.messages.CreatePopulationMessage;
import myapplication.messages.CreatedPopulationMessage;
import myapplication.messages.MutateMessage;
import myapplication.messages.PrintBestIndividualMessage;
import myapplication.messages.ResponseMessage;
import myapplication.messages.StartMessage;

public class KnapsackActor extends Actor {

	private static final int POP_SIZE = 100000;
	private static final double PROB_MUTATION = 0.5;
	private static final int TOURNAMENT_SIZE = 3;

	private IndividualActor[] population = new IndividualActor[POP_SIZE];
    private Random r = new Random();


    //aqui tenho de fazer launch ao individual actor
    // o individual vai receber as mensagens do knapsack e vai enviar para o knapsack um responde message
    private int responsesReceived = 0;

    public KnapsackActor() {
        populateInitialPopulationRandomly();
    }

    private void populateInitialPopulationRandomly() {
		/* Creates a new population, made of random individuals */
		for (int i = 0; i < POP_SIZE; i++) {
            this.send(new CreatePopulationMessage(i), population[i].getAddress());
		}
	}

    @Override
    protected void handleMessage(Message m) { //o generationsActor foi quem mandou a msg
        // knapsack envia a individual msg fitness, best individual, tournament, mutation
        // isto é como se fosse a main
        // o knapsack começa a mandar as mensagens quando recebe a msg start? 
        
        if (m instanceof StartMessage sm) { 
            System.out.println("StartMessage received");

            // for (int i = 0; i < POP_SIZE; i++) {
            //     this.send(new StartMessage(), population[i].getAddress());
            // }
            
            // Step1 - Calculate Fitness
			for (int i = 0; i < POP_SIZE; i++) {
				this.send(new CalculateFitnessMessage(), population[i].getAddress());
			}

			// Step2 - Print the best individual so far.
			IndividualActor best = bestOfPopulation();
            this.send(new PrintBestIndividualMessage(best), m.getSenderAddress());

			// Step3 - Find parents to mate (cross-over)
			IndividualActor[] newPopulation = new IndividualActor[POP_SIZE];
			newPopulation[0] = best; // The best individual remains

			for (int i = 1; i < POP_SIZE; i++) {
				// We select two parents, using a tournament.
				IndividualActor parent1 = tournament(TOURNAMENT_SIZE, r);
				IndividualActor parent2 = tournament(TOURNAMENT_SIZE, r);

				newPopulation[i] = parent1.crossoverWith(parent2, r);
			}

			// Step4 - Mutate
			for (int i = 1; i < POP_SIZE; i++) {
                this.send(new MutateMessage(), population[i].getAddress());
				if (r.nextDouble() < PROB_MUTATION) {
					newPopulation[i].mutate(r);
				}
			}
			population = newPopulation;

        }
        else if (m instanceof CreatedPopulationMessage cpm) {
            population[cpm.getIndividualId()] = cpm.getIndividual();
        }
        else if (m instanceof ResponseMessage rm) {
            System.out.println("ResultMessage received");
            responsesReceived++;
            // 4 msg recebidas -> fitness, best individual, tournament, mutation
            if (responsesReceived == 2) {
                this.send(new ResponseMessage(), m.getSenderAddress());
                System.out.println("All responses received");
               // this.send(new SystemKillMessage(), this.getAddress());
            }
        }
        else {
            System.out.println("Unknown message received");
        }
    }

    private IndividualActor bestOfPopulation() {
		/*
		 * Returns the best individual of the population.
		 */
		IndividualActor best = population[0];
		for (IndividualActor other : population) {
			if (other.fitness > best.fitness) {
				best = other;
			}
		}
		return best;
	}

    private IndividualActor tournament(int tournamentSize, Random r) {
		/*
		 * In each tournament, we select tournamentSize individuals at random, and we
		 * keep the best of those.
		 */
		IndividualActor best = population[r.nextInt(POP_SIZE)];
		for (int i = 0; i < tournamentSize; i++) {
			IndividualActor other = population[r.nextInt(POP_SIZE)];
			if (other.fitness > best.fitness) {
				best = other;
			}
		}
		return best;
	}
    
}
