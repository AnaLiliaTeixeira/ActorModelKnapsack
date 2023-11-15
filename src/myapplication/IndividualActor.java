package myapplication;

import java.util.Random;

import library.Actor;
import library.messages.Message;
import myapplication.messages.CalculateFitnessMessage;
import myapplication.messages.CreatePopulationMessage;
import myapplication.messages.CreatedPopulationMessage;
import myapplication.messages.MutateMessage;
import myapplication.messages.ResponseMessage;
import myapplication.messages.TournamentMessage;

public class IndividualActor extends Actor {

    // This is the definition of the problem
	public static final int GENE_SIZE = 1000; // Number of possible items
	public static int[] VALUES = new int[GENE_SIZE];
	public static int[] WEIGHTS = new int[GENE_SIZE];
	public static int WEIGHT_LIMIT = 300;

    private Random r = new Random();

	static {
		// This code initializes the problem.
		Random r = new Random(1L);
		for (int i = 0; i < GENE_SIZE; i++) {
			VALUES[i] = r.nextInt(100);
			WEIGHTS[i] = r.nextInt(100);
		}
	}

    /*
	 * This array corresponds to whether the object at a given index
	 * is selected to be placed inside the knapsack.
	 * The goal is to find the items that maximize the total value with
	 * surpassing the weight limit.
	 */
	public boolean[] selectedItems = new boolean[GENE_SIZE];
	public int fitness;

    public static IndividualActor createRandom(Random r, IndividualActor ind) {
		for (int i = 0; i < GENE_SIZE; i++) {
			ind.selectedItems[i] = r.nextBoolean();
		}
		return ind;
	}

    @Override
    protected void handleMessage(Message m) {

        // aqui o individual recebe as msgs e no fim envia um response message para o knapsack que deve ser o pai dele
        // pq é o knapsack que o cria 
        if (m instanceof CreatePopulationMessage cpm) {
            IndividualActor created = IndividualActor.createRandom(r, cpm.getIndividual());
            this.send(new CreatedPopulationMessage(cpm.getIndividualId(), created), m.getSenderAddress());

        } else if (m instanceof CalculateFitnessMessage cfm) {    
            this.measureFitness();

        } else if (m instanceof TournamentMessage tm) {

        } else if (m instanceof MutateMessage mm) {

        }

        this.send(new ResponseMessage(), m.getSenderAddress());
    }

    /*
	 * This method evaluates how good a solution the current individual is.
	 * Returns +totalValue if within the weight limit, otherwise returns
	 * -overlimit. The goal is to maximize the fitness.
	 */
	public void measureFitness() {
		int totalWeight = 0;
		int totalValue = 0;
		for (int i = 0; i < GENE_SIZE; i++) {
			if (selectedItems[i]) {
				totalValue += VALUES[i];
				totalWeight += WEIGHTS[i];
			}
		}
		if (totalWeight > WEIGHT_LIMIT) {
			this.fitness = -(totalWeight - WEIGHT_LIMIT);
		} else {
			this.fitness = totalValue;
		}
	}

	/*
	 * Generates a random point in the genotype (selected Items)
	 * Until that point, uses genes from dad (current)
	 * After that point, uses genes from mom (mate)
	 */
	public IndividualActor crossoverWith(IndividualActor mate, Random r) {
		IndividualActor child = new IndividualActor();
		int crossoverPoint = r.nextInt(GENE_SIZE);
		for (int i = 0; i < GENE_SIZE; i++) {
			if (i < crossoverPoint) {
				child.selectedItems[i] = this.selectedItems[i];
			} else {
				child.selectedItems[i] = mate.selectedItems[i];
			}
		}
		return child;
	}

	public void mutate(Random r) {
		int mutationPoint = r.nextInt(GENE_SIZE);
		this.selectedItems[mutationPoint] = !this.selectedItems[mutationPoint];
	}
}
