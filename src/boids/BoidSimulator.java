package boids;

import events.Event;
import gui.GUISimulator;

import java.util.ArrayList;

public class BoidSimulator extends Event {
    private ArrayList<Boid> boids = new ArrayList<Boid>();
    // Nombre de Boids
    private int n;

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
