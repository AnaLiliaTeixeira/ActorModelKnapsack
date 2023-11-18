package myapplication.actors;

import library.messages.Message;

public class CrossoverMessage extends Message {

    private int pop_size;
    private int tournamentSize;
    private Individual[] population;
    private Individual best;

    public CrossoverMessage(int pop_size, int tournamentSize, Individual[] population, Individual best) {
        this.pop_size = pop_size;
        this.tournamentSize = tournamentSize;
        this.population = population;
        this.best = best;
    }

    public int getPopSize() {
        return this.pop_size;
    }

    public int getTournamentSize() {
        return this.tournamentSize;
    }

    public Individual[] getPopulation() {
        return this.population;
    }

    public Individual getBest() {
        return this.best;
    }
}
