package JeuDeLaVie;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import java.awt.*;

public class GrilleCellule extends GrilleCelluleGeneral implements Simulable {

    private  final int[][] init;
    private int nbGeneration;

    /**
     * Grille d'un jeu de la vie. Elle est formé de cellule et permet l'actualisation du jeu, l'affichage à la grille et l'implémentation de Simulable
     * @param n taille selon l'axe des abscisses de la grille
     * @param m taille selon l'axe des ordonées de la grille
     * @param window fenêtre GUI de l'implémentation graphique
     * @param start booléen permettant de savoir si nous sommes à l'initialisation ou non  de notre jeu de la vie
     * @param init Liste contenant les coordonées des cellules qui sont vivantes au début de la simulation
     * @param nbGeneration Permet de savoir la génération actuel lors du déroulement du jeu de la vie
     */

    public GrilleCellule(int n, int m, GUISimulator window, boolean start, int[][] init, int nbGeneration) {
        /**
         * Constructeur de notre grille du jeu de la vie, reprennant le constructeur du grille général, et identifiant si l'on se trouve sur une initialisation
         ou non de notre jeu de la vie
         *
         */
        super(n, m, window, nbGeneration);
        this.init = init;
        this.tab = new CelluleGeneral[n][m];
        if(start) {
            this.nbGeneration=0;
            this.restart();

        }else{
            this.setNbGeneration(nbGeneration);
            this.restartVierge();
        }
    }



    @Override
    protected void restartVierge() {
        /**
         * Fonction permettant de générer une nouvelle grille de cellule et de nettoyer la fenêtre graphique afin de préparer l'affichage de la grille
         */
        super.restartVierge();
        for(int i = 0; i< this.n; i++){
            for(int j = 0; j < this.m; j++){
                this.tab[i][j] = new Cellule(i,j,0);
                window.addGraphicalElement(new Rectangle(10+10*i, 10+10* j, Color.BLACK, Color.WHITE, 10));
            }
        }
    }

    public int getNbVoisin(Cellule cel){
        /**
         * Permet d'obtenir le nombre de voisin d'une cellule donné. On considère comme case voisine une le carré de 8 case possédant une arrête ou un sommet
         en commun avec notre cellule. Un voisin est comptabilisé si ce voisin est vivant.
         * La fonction retourne le nombre de voisin ainsi calculé
         */
        int x = cel.getX();
        int y = cel.getY();
        int compteur = 0;
        if(this.tab[(x-1+this.n)%this.n][(y-1 +this.n)%this.m].getEtatCourant()==1){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y-1+this.n)%this.m].getEtatCourant()==1){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y-1+this.n)%this.m].getEtatCourant()==1){
            compteur+=1;
        }
        if(this.tab[(x-1+this.n)%this.n][(y)%this.m].getEtatCourant()==1){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y)%this.m].getEtatCourant()==1){
            compteur+=1;
        }
        if(this.tab[(x-1+this.n)%this.n][(y+1)%this.m].getEtatCourant()==1){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y+1)%this.m].getEtatCourant()==1){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y+1)%this.m].getEtatCourant()==1){
            compteur+=1;
        }
        return compteur;
    }

    GrilleCellule nextStep(){
        /**
         * Fonction permettant de générer et d'actualiser l'étape n+1 de notre jeu de la vie, en créant une nouvelle grille. Cette fonction ne gère pas l'affichage graphique.
         */
        GrilleCellule new_grille = new GrilleCellule(this.n, this.m, this.window, false, this.init, this.getNbGeneration());
        for (int i = 0; i < this.n; i++){
            for(int j = 0; j < this.m; j++){
                if(this.tab[i][j].getEtatCourant()==1){
                    if(getNbVoisin((Cellule) this.tab[i][j]) < 2){
                        new_grille.tab[i][j].setEtatCourant(0);

                    }
                    else if(getNbVoisin((Cellule) this.tab[i][j]) == 2 || getNbVoisin((Cellule) this.tab[i][j]) ==3){
                        new_grille.tab[i][j].setEtatCourant(1);
                    }
                    else if(getNbVoisin((Cellule) this.tab[i][j]) > 3) {
                        new_grille.tab[i][j].setEtatCourant(0);
                    }
                }
                else{
                    if(getNbVoisin((Cellule) this.tab[i][j]) == 3){
                        new_grille.tab[i][j].setEtatCourant(1);
                    }else{
                        new_grille.tab[i][j].setEtatCourant(0);
                    }
                }
            }
        }
        return new_grille;
    }


    @Override
    public void next() {
        /**
         * Implémentation de la méthode next. La fonction récupère la nouvelle grille généré par nextStep et s'occupe de son affichage.
         * La fonction s'occupe également de gérer le nombre de génération effectué.
         */
        this.setNbGeneration(this.getNbGeneration() + 1);
        GrilleCellule newGrilleCellule=this.nextStep();
        this.tab = newGrilleCellule.tab;
        for (int i = 0; i < this.n ; i++) {
            for (int j = 0; j < this.m; j++) {
                if(this.tab[i][j].getEtatCourant()==1){
                    window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i, Color.BLACK, Color.BLACK, 10));
                }
            }
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :" + this.getNbGeneration()));
    }

    @Override
    public void restart() {
        /**
         * Implémentation de la méthode restart. Permet de recomencer une nouvelle grille en se basant sur le tableau d'initialisation que l'on conserve tout au long de la simulation.
         */
        this.window.reset();
        for(int i = 0; i< this.n; i++){
            for(int j = 0; j < this.m; j++){
                this.tab[i][j] = new Cellule(i,j,0);
                window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i, Color.BLACK, Color.WHITE, 10));
            }
        }
        for (int i = 0; i < this.init[0].length; i++) {
            this.tab[this.init[0][i]][this.init[1][i]].setEtatCourant(1);
            window.addGraphicalElement(new Rectangle(10+10*this.init[1][i], 10+10* this.init[0][i], Color.BLACK, Color.BLACK, 10));
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :" + this.nbGeneration));
    }
}
