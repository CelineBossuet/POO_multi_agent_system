package boids;

import java.awt.*;

public class NormalBoid extends Boid{
    /**
     * Classe d'un boid normal, ayant une force de base de 1.8
     * @param x
     * @param y
     */
    public NormalBoid(float x, float y){
        super(x, y, Color.WHITE);
        this.force = 1.8f;
    }
}
