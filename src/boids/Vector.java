package boids;

import java.awt.Point;

public class Vector extends Point{

    public Vector(int x, int y){
        super(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        return ((Vector)obj).x == this.x && ((Vector)obj).y == this.y;
    }

    public void add(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
    }

    public void sub(Vector vector){
        this.x -= vector.x;
        this.y -= vector.y;
    }

    public Vector substract(Vector v){
        return new Vector(this.x - v.x, this.y - v.y);
    }

    public void divide(int n){
        this.x /= n;
        this.y /= n;
    }

    public Vector division(int n){
        return new Vector(this.x / n, this.y / n);
    }
}
