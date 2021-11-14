package boids;

public class Vector{
    private float x;
    private float y;

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void add(Vector vect){
        this.x += vect.x;
        this.y += vect.y;
    }

    static public Vector sub(Vector vect1, Vector vect2) {
        return new Vector(vect1.x - vect2.x, vect1.y - vect2.y);
    }

    public void mult(float n) {
        this.x *= n;
        this.y *= n;
    }

    public void div(float n) {
        this.x /= n;
        this.y /= n;
    }

    public float angle(Vector vect1, Vector vect2) {
        double scalaire = vect1.x * vect2.x + vect1.y * vect2.y;
        double normeV1 = Math.sqrt(Math.pow(vect1.x, 2) + Math.pow(vect1.y, 2));
        double normeV2 = Math.sqrt(Math.pow(vect2.x, 2) + Math.pow(vect2.y, 2));
        return (float) Math.acos(scalaire / (normeV1 * normeV2));
    }

    public float norme(){
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    static public float distance(Vector vect1, Vector vect2) {
        float dx = vect1.x - vect2.x;
        float dy = vect1.y - vect2.y;
        return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public void normalize() {
        float n = norme();
        if (n != 0) {
            div(n);
        }
    }

    public void limit(float max) {
        if (norme() > max) {
            normalize();
            mult(max);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return ((Vector)obj).x == this.x && ((Vector)obj).y == this.y;
    }
}
