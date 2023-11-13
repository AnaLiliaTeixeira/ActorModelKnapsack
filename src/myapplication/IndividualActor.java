package myapplication;

import java.util.Random;

import library.Actor;
import library.messages.Message;
import myapplication.messages.CalculateFitnessMessage;
import myapplication.messages.MutateMessage;
import myapplication.messages.PrintBestIndividualMessage;
import myapplication.messages.ResponseMessage;
import myapplication.messages.TournamentMessage;

public class IndividualActor extends Actor {

    // This is the definition of the problem
	public static final int GENE_SIZE = 1000; // Number of possible items
	public static int[] VALUES = new int[GENE_SIZE];
	public static int[] WEIGHTS = new int[GENE_SIZE];
	public static int WEIGHT_LIMIT = 300;

	static {
		// This code initializes the problem.
		Random r = new Random(1L);
		for (int i = 0; i < GENE_SIZE; i++) {
			VALUES[i] = r.nextInt(100);
			WEIGHTS[i] = r.nextInt(100);
		}
	}

    @Override
    protected void handleMessage(Message m) {

        // aqui o individual recebe as msgs e no fim envia um response message para o knapsack que deve ser o pai dele
        // pq é o knapsack que o cria 
        if (m instanceof CalculateFitnessMessage cfm) {

        } else if (m instanceof PrintBestIndividualMessage pbim) {

        } else if (m instanceof TournamentMessage tm) {

        } else if (m instanceof MutateMessage mm) {

        }
        
        this.send(new ResponseMessage(), m.getSenderAddress());
    }
    
}
