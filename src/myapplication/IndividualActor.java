package myapplication;

import java.util.Random;

import library.Actor;
import library.messages.Message;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleMessage'");
    }
    
}
