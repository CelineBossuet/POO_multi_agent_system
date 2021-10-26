package boids;
import java.util.ArrayList;

public class Boids {
    private ArrayList<Boid> boids;

    public Boids(){
        this.boids = new ArrayList<Boid>();
    }

    public ArrayList<Boid> get(){
        return this.boids;
    }

    public void add(Boid boid){
        this.boids.add(boid);
    }

}
