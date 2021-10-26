package boids;

import gui.GUISimulator;

import java.awt.*;

public class TestBoids {

    public static void main(String[] args){
        Boids boids = new Boids();
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);

        boids.add(new Boid(10, 50));
        boids.add(new Boid(100, 50));
        boids.add(new Boid(30, 100));
        boids.add(new Boid(500, 500));
        boids.add(new Boid(700, 200));
        boids.add(new Boid(300, 600));
        boids.add(new Boid(200, 500));
        boids.add(new Boid(900, 100));
        boids.add(new Boid(950, 60));

        gui.setSimulable(new BoidSimulator(gui, boids));
    }
}
