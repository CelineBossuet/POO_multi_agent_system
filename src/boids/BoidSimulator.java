package boids;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public class BoidSimulator implements Simulable {
    private GUISimulator gui;
    private Boids boids;

    public BoidSimulator(GUISimulator gui, Boids boids){
        super();
        this.gui = gui;
        this.boids = boids;
    }

    @Override
    public void next(){
        gui.reset();
        this.update();
        for(Boid boid: this.boids.get()) {
            gui.addGraphicalElement(new Rectangle(boid.getPosition().x, boid.getPosition().y, Color.WHITE, Color.WHITE, 10));
        }
    }

    @Override
    public void restart(){
        gui.reset();
    }

    public void update(){
        for(Boid boid: this.boids.get()){
            this.rule1(boid);
            this.rule2(boid);

            boid.addVelocity(boid.getAcceleration());
            boid.addPosition(boid.getVelocity());
        }
    }

    // Voler autour du centre de masse des voisins
    private void rule1(Boid boid){
        Vector center = new Vector(0, 0);
        int n = this.boids.get().size();

        for(Boid b: this.boids.get()){
            if(!b.equals(boid) && this.near(boid, b)){
                center.add(b.getPosition());
            }
        }

        if(n != 0){
            center.divide(n);
        }

        boid.addAcceleration(center.substract(boid.getPosition()).division(100));
    }

    // Pas de collisions
    private void rule2(Boid boid){
        Vector c = new Vector(0, 0);

        for(Boid b: this.boids.get()){
            if(!b.equals(boid) && this.near(boid, b)){
                if(Math.abs(boid.getPosition().distance(b.getPosition())) < 100){
                    c.sub(b.getPosition().substract(boid.getPosition()));
                }

            }
        }

        boid.addAcceleration(c);
    }



    private boolean near(Boid boid1, Boid boid2){

        // Distance
        int distance = 10;

        if(boid1.getPosition().distance(boid2.getPosition()) > distance){
            return false;
        }else{
            return true;
        }


    }
}
