package myapplication.actors;
import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.FitnessMeasuredMessage;
import myapplication.messages.MeasureFitnessMessage;

public class MeasureFitnessActor extends Actor {

    private static final int POP_SIZE = 100000;
    private Individual[] population = new Individual[POP_SIZE];
    private int individualCounter = 0;

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof MeasureFitnessMessage mm) {
            individualCounter++;
            
			mm.getIndividual().measureFitness();  

            population[individualCounter-1] = mm.getIndividual(); 
            if (individualCounter == POP_SIZE) {
                this.send(new FitnessMeasuredMessage(population, mm.getGeneration()), m.getSenderAddress());
                individualCounter = 0;
            }
        }
    }
}
