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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
