package boids;

import java.awt.*;

public class NormalBoid extends Boid{
    public NormalBoid(float x, float y){
        super(x, y, Color.WHITE);
        this.force = 1.5f;
    }
}
