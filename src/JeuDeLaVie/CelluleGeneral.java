package JeuDeLaVie;

public class CelluleGeneral{
    private final int x;
    private final int y;
    private final int nbEtat;
    private int etatCourant;

    public CelluleGeneral(int x, int y, int nbEtat, int etatCourant) {
        this.x = x;
        this.y = y;
        this.nbEtat = nbEtat;
        this.etatCourant = etatCourant;
    }

    @Override
    public String toString() {
        return "La cellule de coordonnées (" +
                "(x=" + x +
                ", y=" + y +
                ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNbEtat() {
        return nbEtat;
    }

    public int getEtatCourant() {
        return etatCourant;
    }

    public void setEtatCourant(int e){
        this.etatCourant = e;
    }
}
