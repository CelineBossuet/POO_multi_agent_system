package boids;

import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;

public abstract class Boid {
    protected Vector position;
    protected Vector velocity;
    protected Vector acceleration;
    protected Vector savedPosition;

    protected Color color;
    protected Triangle triangle;

    protected float force;

    protected float maxVelocity;
    protected float maxForce;

    public static GUISimulator gui;


    public Boid(float x, float y, Color color) {
        this.position = new Vector(x,y);
        this.savedPosition = new Vector(x,y);

        this.acceleration = new Vector((float)( Math.random() - 0.5), (float) (Math.random() - 0.5));
        this.velocity = new Vector((float)( Math.random() - 0.5), (float) (Math.random() - 0.5));

        this.color = color;
        this.triangle = new Triangle((int)x, (int)y, velocity, this.color, 5);

        this.maxVelocity = 5f;
        this.maxForce = 0.6f;

        // On dessine dès le départ le Boid
        gui.addGraphicalElement(this.triangle);
    }

    public void reset() {
        this.position.set(this.savedPosition.getX(), this.savedPosition.getY());

        this.triangle.translate((int)(this.position.getX() - this.triangle.getX()), (int)(this.position.getY() - this.triangle.getY()));
    }

    /*
     * On met à jour les attributs du Boid
     */
    public void update() {
        this.velocity.add(this.acceleration);

        this.velocity.limit(this.maxVelocity);

        this.position.add(this.velocity);

        this.acceleration.set(0, 0);

        this.triangle.translate((int)(this.position.getX() - this.triangle.getX()), (int)(this.position.getY() - this.triangle.getY()));
    }


    /**
     * Permet d'éviter les collisions avec ses voisins
     * @return Une force
     */
    public Vector collisions(ArrayList<Boid> boids) {
        float distanceMax = 50;
        int n = 0;

        Vector total = new Vector(0, 0);


        for (Boid boid : boids) {
            float distance = Vector.distance(this.position, boid.position);

            if (distance > 0 && distance < distanceMax) {
                float angle = this.velocity.angle(this.velocity, new Vector(boid.position.getX() - this.position.getX(),boid.position.getY() - this.position.getY()));

                if( (angle < 3*Math.PI/4 && angle > -3*Math.PI/4) || (Math.round(this.velocity.getX()) == 0 && Math.round(this.velocity.getY()) == 0) ) {
                    Vector sub = Vector.sub(this.position, boid.position);

                    sub.normalize();

                    total.add(sub);
                    n++;
                }
            }
        }
        if (n == 0) {
            return new Vector(0 ,0);
        }else{
            total.div(n);

            total.normalize();
            total.mult(maxVelocity);

            Vector force = Vector.sub(total, this.velocity);
            force.limit(maxForce);

            return force;
        }
    }

    /**
     * Permet de se diriger dans la même direction que les voisins
     * @return Une force
     */
    public Vector direction(ArrayList<Boid> boids) {
        float vision = 50;
        int n = 0;

        Vector total = new Vector(0,0);

        for (Boid boid : boids) {
            float distance = Vector.distance(this.position, boid.position);
            if (distance > 0 && distance < vision) {
                float angle = this.velocity.angle(this.velocity, new Vector(boid.position.getX() - this.position.getX(),boid.position.getY() - this.position.getY()));
                if( ( angle < 3*Math.PI/4 && angle > -3*Math.PI/4 ) || (Math.round(this.velocity.getX()) == 0 && Math.round(this.velocity.getY()) == 0) ) {

                    total.add(boid.velocity);
                    n++;

                }
            }
        }

        if (n == 0) {
            return new Vector(0,0);
        } else {
            total.div(n);

            total.normalize();
            total.mult(maxVelocity);

            Vector force = Vector.sub(total, velocity);
            force.limit(maxForce);

            return force;
        }
    }

    /**
     * Règle qui permet aux boids de se diriger vers le centre de ses voisins
     * @return Une force
     */
    public Vector cohesion(ArrayList<Boid> boids) {
        float vision = 50;
        int n = 0;
        
        Vector total = new Vector(0,0);
        
        for (Boid boid : boids) {
            float distance = Vector.distance(this.position, boid.position);
            
            if (distance > 0 && distance < vision) {
                float angle = this.velocity.angle(this.velocity, new Vector(boid.position.getX() - this.position.getX(),boid.position.getY() - this.position.getY()));

                if( (angle < 3*Math.PI/4 && angle > -3*Math.PI/4 ) || ( Math.round(this.velocity.getX()) == 0 && Math.round(this.velocity.getY()) == 0) ) {

                    total.add(boid.position);

                    n++;
                }
            }
        }
        
        if (n == 0) {
            return new Vector(0,0);

        } else {
            total.div(n);

            // On prend toutes les targets et on renvoie une force vers ces targets
            Vector result = Vector.sub(total, this.position);

            result.normalize();
            result.mult(this.maxVelocity);

            Vector force = Vector.sub(result, this.velocity);
            force.limit(this.maxForce);

            return force;
        }
    }


    public void addRules(ArrayList<Boid> boids) {
        Vector rule1 = collisions(boids);
        Vector rule2 = direction(boids);
        Vector rule3 = cohesion(boids);

        // On fait en sorte que quand un Boid depasse la limite il va à l'autre extrémité
        int width = this.gui.getPanelWidth();
        int height = this.gui.getPanelHeight();

        if (this.position.getX() < 0) {
            this.position.setX(width);
        }

        if (this.position.getX() > width) {
            this.position.setX(0);
        }

        if (this.position.getY() < 0) {
            this.position.setY(height);
        }
        
        if (this.position.getY() > height) {
            this.position.setY(0);
        }

        // On applique toutes les forces
        this.acceleration.add(rule1);
        this.acceleration.add(rule2);
        this.acceleration.add(rule3);
    }
}
