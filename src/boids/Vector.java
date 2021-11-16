package boids;

public class Vector{
    private float x;
    private float y;

    /**
     * Classe qui gère les vecteurs
     * @param x
     * @param y
     */
    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return x
     */
    public float getX(){
        return this.x;
    }

    /**
     * @return y
     */
    public float getY(){
        return this.y;
    }

    /**
     * Set le vecteur
     * @param x
     * @param y
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set x
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Set y
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Ajoute un vecteur au vecteur courant
     * @param vect
     */
    public void add(Vector vect){
        this.x += vect.x;
        this.y += vect.y;
    }

    /**
     *
     * @param vect1
     * @param vect2
     * @return nouveau vecteur contenant vect1 - vect2
     */
    static public Vector sub(Vector vect1, Vector vect2) {
        return new Vector(vect1.x - vect2.x, vect1.y - vect2.y);
    }

    /**
     * Multiplie le vecteur courant par n
     * @param n
     */
    public void mult(float n) {
        this.x *= n;
        this.y *= n;
    }

    /**
     * Divise le vecteur courant par n
     * @param n
     */
    public void div(float n) {
        this.x /= n;
        this.y /= n;
    }

    /**
     * @param vect1
     * @param vect2
     * @return L'angle entre deux vect1 et vect2
     */
    public float angle(Vector vect1, Vector vect2) {
        double scalaire = vect1.x * vect2.x + vect1.y * vect2.y;
        double normeV1 = Math.sqrt(Math.pow(vect1.x, 2) + Math.pow(vect1.y, 2));
        double normeV2 = Math.sqrt(Math.pow(vect2.x, 2) + Math.pow(vect2.y, 2));
        return (float) Math.acos(scalaire / (normeV1 * normeV2));
    }

    /**
     * @return la norme du vecteur courant
     */
    public float norme(){
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * @param vect1
     * @param vect2
     * @return la distance entre vect1 et vect2
     */
    static public float distance(Vector vect1, Vector vect2) {
        float dx = vect1.x - vect2.x;
        float dy = vect1.y - vect2.y;
        return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Normalise le vecteur courant
     */
    public void normalize() {
        float n = norme();
        if (n != 0) {
            div(n);
        }
    }

    /**
     * Permet de linmiter un vecteur par une valeur max
     * @param max
     */
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
