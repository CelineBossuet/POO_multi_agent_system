package boids;

import events.Event;
import gui.GUISimulator;

import java.util.ArrayList;

public class BoidSimulator extends Event {
    private ArrayList<Boid> boids = new ArrayList<Boid>();
    // Nombre de Boids
    private int n;

    /**
     * Simulateur de Boids qui utilise l'event manager
     * @param gui
     * @param date
     * @param n
     */
    public BoidSimulator(GUISimulator gui, long date, int n) {
        super(date);
        Boid.gui = gui;
        this.n = n;

        int width = gui.getPanelWidth();
        int height = gui.getPanelHeight();

        for (int i = 0; i < this.n; i++) {
            this.boids.add(new NormalBoid((int)(width * Math.random()), (int)(height * Math.random())));
        }
    }

    /**
     * À chaque tour ajoute les règles et met à jour tous les boids, change la date
     */
    public void execute() {
        for (Boid boid: this.boids) {
            boid.addRules(boids);
        }

        for (Boid boid: this.boids) {
            boid.update();
        }

        this.setDate(this.getDate() + 1);

        getManager().addEvent(this);
    }

    /**
     * Reset tous les boids
     */
    public void reset() {
        for (Boid boid: boids) {
            boid.reset();
        }

        for (Boid boid: this.boids) {
            boid.update();
        }

        this.setDate(0);
    }
}
