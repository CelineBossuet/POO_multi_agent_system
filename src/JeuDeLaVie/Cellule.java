package JeuDeLaVie;

public class Cellule extends CelluleGeneral {
    private boolean estVivante;

    public Cellule(int x, int y, boolean estVivante) {
        super(x,y);
        this.estVivante = estVivante;
    }


    public boolean isEstVivante() {
        return estVivante;
    }

    public void setEstVivant(boolean estVivante) {
        this.estVivante = estVivante;
    }

    @Override
    public String toString() {
        return super.toString() + "estVivante?=" + estVivante;
    }
}
