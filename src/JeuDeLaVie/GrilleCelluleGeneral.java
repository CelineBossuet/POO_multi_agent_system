package JeuDeLaVie;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Text;

import java.awt.*;

public class GrilleCelluleGeneral {

    protected GUISimulator window;
    protected int n;
    protected int m;
    private int nbGeneration;
    protected CelluleGeneral[][] tab;
    protected Color[] tableCouleur = {Color.WHITE, Color.CYAN, Color.BLUE, Color.GREEN,Color.PINK, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK};

    /**
     * Classe mère des Grille permetant l'implémentation de certaines fonctionalités non spécifiques à l'automate cellulaire considéré
     *
     * @param n taille en abscisse de la Grille
     * @param m taille en ordonée de la grille
     * @param window fenêtre GUI de l'implémentation graphique
     * @param nbGeneration Permet de savoir la génération actuel lors du déroulement du jeu de la vie
     */

    public GrilleCelluleGeneral(int n, int m, GUISimulator window, int nbGeneration) {
        /**
         * Constructeur de la classe grille cellule général
         */
        this.n = n;
        this.m = m;
        this.window = window;
        this.nbGeneration = nbGeneration;
        this.tab = new CelluleGeneral[n][m];
    }

    public void setNbGeneration(int nbGeneration) {
        /**
         * Permet de fixer le numéro de la génération de la grille courante
         */
        this.nbGeneration = nbGeneration ;
    }

    public int getNbGeneration(){
        /**
         * Retourne le numéro de la génération de la grille courante
         */
        return nbGeneration;
    }

    public void affichageGraphique(){
        /**
         * Permet d'afficher la grille dans les méthode next correspondates
         */
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i,  Color.BLACK, this.tableCouleur[this.tab[i][j].getEtatCourant()], 10));
            }
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :"+ this.getNbGeneration()));
    }


    void restartVierge() {
        /**
         * Permet de raffraichir la fenêtre pour démarer un nouvel affichage
         */
        this.window.reset();
    }
}
