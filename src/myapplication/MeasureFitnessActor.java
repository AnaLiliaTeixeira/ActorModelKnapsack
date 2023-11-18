package myapplication;
import library.Actor;
import library.messages.Message;
import myapplication.actors.FitnessMeasuredMessage;
import myapplication.actors.Individual;
import myapplication.actors.MeasureFitnessMessage;

public class MeasureFitnessActor extends Actor {

    private Individual[] population;

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof MeasureFitnessMessage mm) {
            population = mm.getPopulation();
            
            for (int i = 0; i < mm.getPopSize(); i++) {
                // System.out.println("Starting measure fitness actor pop= " + i);
				population[i].measureFitness();   
			}
            this.send(new FitnessMeasuredMessage(population, mm.getGeneration()), m.getSenderAddress());
            // System.out.println("Finished measure fitness generation= " + mm.getGeneration());
        }
    }
    
}
