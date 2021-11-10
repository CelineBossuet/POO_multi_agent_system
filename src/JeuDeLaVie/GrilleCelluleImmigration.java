package JeuDeLaVie;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import java.awt.*;

public class GrilleCelluleImmigration extends GrilleCelluleGeneral implements Simulable {


    private final int nbEtat;
    private final int[][] init;

    /**
     * Classe permettant la génération et la simulation du jeu de l'immigration
     *
     * @param n taille en abscisse de la grille
     * @param m taille en ordonée de la grille
     * @param window fenêtre GUI de l'implémentation graphique
     * @param nbGeneration numéro de la génération de la grille courante
     * @param nbEtat nombre d'état possible pour chaque cellule de la grille
     * @param init matrice d'initialisation de la simulation du jeu de l'immigration
     * @param start booléen permettant de savoir si l'on doit ou non réinitialiser la grille
     */

    public GrilleCelluleImmigration(int n, int m, GUISimulator window,int nbGeneration, int nbEtat, int[][] init, boolean start) {
        /**
         * Constructeur de la grille d'immigration
         */
        super(n, m, window, nbGeneration);
        this.init = init;
        this.nbEtat = nbEtat;
        this.tab = new CelluleGeneral[n][m];
        if(this.tableCouleur.length < nbEtat){
            System.out.println("Trop d'état pour l'implémentation graphique");
            System.exit(1);
        }
        if(start){
            this.restart();
        }else{
            this.restartVierge();
        }
    }

    public int getNbVoisin(CelluleGeneral cel){
        /**
         * Permet de trouver le nombre de voisin d'une cellule. On considère comme case voisine une le carré de 8 case possédant une arrête ou un sommet
         en commun avec notre cellule. Une cellule est considéré comme voisine si son état est d'un cran plus élevé que celui de la cellule de base modulo le
         nombre de génération possible
         *
         */
        int next_state=(cel.getEtatCourant()+1)%this.nbEtat;
        int x = cel.getX();
        int y = cel.getY();
        int compteur = 0;
        if(this.tab[(x-1+this.n)%this.n][(y-1 +this.n)%this.m].getEtatCourant()==next_state){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y-1+this.n)%this.m].getEtatCourant()==next_state){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y-1+this.n)%this.m].getEtatCourant()==next_state){
            compteur+=1;
        }
        if(this.tab[(x-1+this.n)%this.n][(y)%this.m].getEtatCourant()==next_state){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y)%this.m].getEtatCourant()==next_state){
            compteur+=1;
        }
        if(this.tab[(x-1+this.n)%this.n][(y+1)%this.m].getEtatCourant()==next_state){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y+1)%this.m].getEtatCourant()==next_state){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y+1)%this.m].getEtatCourant()==next_state){
            compteur+=1;
        }
        return compteur;
    }

    GrilleCelluleImmigration nextStep() {
        /**
         * Fonction permettant de générer la grille de la prochaine étape et d'actualiser cette dernière. Cette fonction ne gère pas l'affichage de la grille.
         */
        GrilleCelluleImmigration new_grille = new GrilleCelluleImmigration(this.n, this.m, this.window, this.getNbGeneration(), this.nbEtat, this.init, false);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if(getNbVoisin(this.tab[i][j]) >=3){
                    new_grille.tab[i][j].setEtatCourant((this.tab[i][j].getEtatCourant() + 1) % this.tab[i][j].getNbEtat());
                }else{
                    new_grille.tab[i][j].setEtatCourant((this.tab[i][j].getEtatCourant()));
                }
            }
        }
        return new_grille;
    }

    @Override
    public void next() {
        /**
         * Implémentation de la méthode next. Permet de gérer l'éffichage de la grille générer dans nextStep.
         */
        this.setNbGeneration(this.getNbGeneration() + 1);
        GrilleCelluleImmigration newGrilleCellule=this.nextStep();
        this.tab = newGrilleCellule.tab;
        this.affichageGraphique();
    }

    @Override
    public void restart() {
        /**
         * Implémentation de restart. Permet de recomencer une nouvelle grille en se basant sur le tableau d'initialisation que l'on conserve tout au long de la simulation.
         */
        super.restartVierge();
        this.setNbGeneration(0);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.tab[i][j] = new CelluleGeneral(i,j,this.nbEtat, this.init[i][j]);
                this.window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i, Color.BLACK, this.tableCouleur[this.tab[i][j].getEtatCourant()], 10));
            }
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :" + this.getNbGeneration()));
    }

    @Override
    protected void restartVierge() {
        /**
         * Fonction permettant de générer une nouvelle grille de cellule et de nettoyer la fenêtre graphique afin de préparer l'affichage de la grille
         */
        super.restartVierge();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.tab[i][j] = new CelluleGeneral(i, j, this.nbEtat, 0);
            }
        }
    }
}
