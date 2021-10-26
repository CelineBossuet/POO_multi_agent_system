package JeuDeLaVie;

public class Cellule {
    private int x;
    private int y;
    private boolean estVivante;

    public Cellule(int x, int y, boolean estVivante) {
        this.x = x;
        this.y = y;
        this.estVivante = estVivante;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEstVivante() {
        return estVivante;
    }

    public void setEstVivant(boolean estVivante) {
        this.estVivante = estVivante;
    }

    @Override
    public String toString() {
        return "La cellule de coordonnées (" +
                "(x=" + x +
                ", y=" + y +
                "), estVivante?=" + estVivante +
                '}';
    }
}
