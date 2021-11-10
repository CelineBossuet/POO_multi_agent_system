package JeuDeLaVie;

public class Cellule extends CelluleGeneral {
    /**
     * Une cellule représente une case de notre Jeu de la Vie, Il descend de la classe Cellule général en en utilisant tous les attributs
     *
     * @param x position sur l'axe des abscisses de la cellule
     * @param y position sur l'axe des ordonées de la cellule
     * @param etat l'état courant de la cellule, qui est ici de 0 ou 1
     */

    public Cellule(int x, int y,  int etat) {
        /**
         * Constructeur de la Cellule qui reprend la forme de la cellule général en fixant nbetat à 2
         */
        super(x,y,2, etat);
    }


    @Override
    public String toString() {
        /**
         * Permet de mettre en string la poition et l'état d'une cellule
         */
        return super.toString() + "estVivante?=" + this.getEtatCourant();
    }
}
