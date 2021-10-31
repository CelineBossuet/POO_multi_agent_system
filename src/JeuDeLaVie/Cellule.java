package JeuDeLaVie;

public class Cellule extends CelluleGeneral {


    public Cellule(int x, int y,  int etat) {
        super(x,y,2, etat);
    }


    @Override
    public String toString() {
        return super.toString() + "estVivante?=" + this.getEtatCourant();
    }
}
