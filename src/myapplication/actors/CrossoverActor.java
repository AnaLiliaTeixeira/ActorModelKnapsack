package myapplication.actors;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.CrossoverMessage;
import myapplication.messages.MutationMessage;

public class CrossoverActor extends Actor {

    private static final int POP_SIZE = 100000;
    private static final double PROB_MUTATION = 0.5;
    private Individual[] population;
	private ThreadLocalRandom r = ThreadLocalRandom.current();

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof CrossoverMessage cm) {
            population = cm.getPopulation();

            Individual[] newPopulation = new Individual[POP_SIZE];
            newPopulation[0] = cm.getBest(); // The best Individual remains

            for (int i = 1; i < cm.getPopSize(); i++) {
                // We select two parents, using a tournament.
                Individual parent1 = tournament(cm.getTournamentSize(), r, population);
                Individual parent2 = tournament(cm.getTournamentSize(), r, population);

                newPopulation[i] = parent1.crossoverWith(parent2, r);
            }

            this.send(new MutationMessage(POP_SIZE, newPopulation, PROB_MUTATION), m.getSenderAddress());
        }
    }

    private Individual tournament(int tournamentSize, Random r, Individual[] population) {
		/*
		 * In each tournament, we select tournamentSize Individuals at random, and we
		 * keep the best of those.
		 */
		Individual best = population[r.nextInt(POP_SIZE)];
		for (int i = 0; i < tournamentSize; i++) {
			Individual other = population[r.nextInt(POP_SIZE)];
			if (other.fitness > best.fitness) {
				best = other;
			}
		}
		return best;
	}
    
}
