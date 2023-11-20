package myapplication.actors;
import library.Actor;
import library.Individual;
import library.messages.Message;
import myapplication.messages.FitnessMeasuredMessage;
import myapplication.messages.MeasureFitnessMessage;

public class MeasureFitnessActor extends Actor {

    private Individual[] population;

    @Override
    protected void handleMessage(Message m) {
        if (m instanceof MeasureFitnessMessage mm) {
            population = mm.getPopulation();
            
            for (int i = 0; i < mm.getPopSize(); i++) {
				population[i].measureFitness();   
			}
            this.send(new FitnessMeasuredMessage(population, mm.getGeneration()), m.getSenderAddress());
        }
    }
    
}
