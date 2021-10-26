package boids;

public class Boid {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;

    public Boid(int x, int y){
        this.position = new Vector(x, y);
        this.velocity = new Vector(10, 10);
        this.acceleration = new Vector(0, 0);
    }

    @Override
    public boolean equals(Object obj) {
        return this.position.equals(((Boid)obj).getPosition()) && this.velocity.equals(((Boid)obj).getVelocity());
    }

    public Vector getPosition(){
        return this.position;
    }

    public void addPosition(Vector newPos){
        this.position.add(newPos);
    }

    public Vector getVelocity(){
        return this.velocity;
    }

    public void addVelocity(Vector newVelocity){
        this.velocity.add(newVelocity);
    }

    public Vector getAcceleration(){
        return this.acceleration;
    }

    public void addAcceleration(Vector acceleration){
        this.acceleration.add(acceleration);
    }


}
