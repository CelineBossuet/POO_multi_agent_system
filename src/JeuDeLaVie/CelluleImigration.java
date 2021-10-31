package JeuDeLaVie;

public class CelluleImigration extends CelluleGeneral{


    public CelluleImigration(int x, int y, int nbEtat, int etatCourant) {
        super(x, y, nbEtat, etatCourant);
    }

    @Override
    public String toString() {
        return super.toString() + "estVivante?=" + this.getEtatCourant();
    }
}
