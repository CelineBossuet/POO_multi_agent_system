package JeuDeLaVie;

public class CelluleGeneral{
    private final int x;
    private final int y;
    private final int nbEtat;
    private int etatCourant;

    /**
     * Une cellule général sert de modèle de base pour les cellules de nos 3 automates cellulaires
     *
     * @param x abscisse de la cellule
     * @param y ordonnée de la cellule
     * @param nbEtat nombre d'Etat possible que peut prendre la cellule
     * @param etatCourant Etat actuel de la cellule
     */

    public CelluleGeneral(int x, int y, int nbEtat, int etatCourant) {
        /**
         * Constructeur d'une cellule général initialisant tous les paramètres
         */
        this.x = x;
        this.y = y;
        this.nbEtat = nbEtat;
        this.etatCourant = etatCourant;
    }

    @Override
    public String toString() {
        /**
         * Permet de mettre sous format string la poition de la cellule
         */
        return "La cellule de coordonnées (" +
                "(x=" + x +
                ", y=" + y +
                ")";
    }

    public int getX() {
        /**
         * retourne l'abscisse d'une cellule
         */
        return x;
    }

    public int getY() {
        /**
         * retourne l'ordonnée d'une cellule
         */
        return y;
    }

    public int getNbEtat() {
        /**
         * retourne le nombre d'état que peut posséder une cellule
         */
        return nbEtat;
    }

    public int getEtatCourant() {
        /**
         * retourne l'état courant d'une cellule
         */
        return etatCourant;
    }

    public void setEtatCourant(int e){
        /**
         * Permet de set l'état courant d'une cellule à un état donné entré en paramètre
         */
        this.etatCourant = e;
    }
}
