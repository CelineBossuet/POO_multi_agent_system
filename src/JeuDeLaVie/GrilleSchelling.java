package JeuDeLaVie;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class GrilleSchelling extends GrilleCelluleGeneral implements Simulable {

    private int nbEtat;
    private int[][] init;
    private Queue<CelluleGeneral> file = new LinkedList<>();
    private int K;

    /**
     * Classe permettant la génération des grilles pour la simulation du modèle de Schelling
     *
     * @param n taille en abscisse de la grill
     * @param m taille en ordonnée de la grille
     * @param window fenêtre GUI de l'implémentation graphique
     * @param nbGeneration numéro de la génération de la grille courante
     * @param nbEtat nombre d'état possible pour une cellule
     * @param init matrice d'initialisation de notre modèle de Schelling
     * @param start booléen permettant de savoir si l'on doit réinitialiser notre grille
     * @param K seuil de voisins étranger à partir duqeul l'on considère qu'un habitant doit déménager
     * @param tab grille de cellule
     */
    public GrilleSchelling(int n, int m, GUISimulator window, int nbGeneration, int nbEtat, int[][] init, boolean start, int K, CelluleGeneral[][] tab) {
        /**
         * Constructeur de la grille de Schelling
         */
        super(n, m, window, nbGeneration);
        this.tab = tab;
        this.nbEtat = nbEtat;
        this.init = init;
        this.K=K;
        if(start){
            this.restart();
        }
    }

    public int getNbVoisinDiff(CelluleGeneral cel){
        /**
         * Permet de trouver le nombre de voisin d'une cellule. On considère comme case voisine une le carré de 8 case possédant une arrête ou un sommet
         en commun avec notre cellule. Une cellule est considéré comme voisine si elle n'est ni d'état 0 (emplacement vacant), ni de la même couleur que
         la cellule d'origine.
         */
        int sameState =cel.getEtatCourant();
        int x = cel.getX();
        int y = cel.getY();
        int compteur = 0;
        if(this.tab[(x-1+this.n)%this.n][(y-1 +this.n)%this.m].getEtatCourant()!= sameState && this.tab[(x-1+this.n)%this.n][(y-1 +this.n)%this.m].getEtatCourant()!= 0 ){
            compteur++;
        }
        if(this.tab[(x)%this.n][(y-1+this.n)%this.m].getEtatCourant()!= sameState && this.tab[(x)%this.n][(y-1+this.n)%this.m].getEtatCourant() != 0){
            compteur++;
        }
        if(this.tab[(x+1)%this.n][(y-1+this.n)%this.m].getEtatCourant()!= sameState && this.tab[(x+1)%this.n][(y-1+this.n)%this.m].getEtatCourant() != 0){
            compteur++;
        }
        if(this.tab[(x-1+this.n)%this.n][(y)%this.m].getEtatCourant()!= sameState && this.tab[(x-1+this.n)%this.n][(y)%this.m].getEtatCourant() != 0){
            compteur++;
        }
        if(this.tab[(x+1)%this.n][(y)%this.m].getEtatCourant()!= sameState && this.tab[(x+1)%this.n][(y)%this.m].getEtatCourant()!= 0){
            compteur++;
        }
        if(this.tab[(x-1+this.n)%this.n][(y+1)%this.m].getEtatCourant()!= sameState && this.tab[(x-1+this.n)%this.n][(y+1)%this.m].getEtatCourant()!= 0){
            compteur++;
        }
        if(this.tab[(x)%this.n][(y+1)%this.m].getEtatCourant()!= sameState && this.tab[(x)%this.n][(y+1)%this.m].getEtatCourant()!= 0){
            compteur++;
        }
        if(this.tab[(x+1)%this.n][(y+1)%this.m].getEtatCourant()!= sameState && this.tab[(x+1)%this.n][(y+1)%this.m].getEtatCourant()!= 0){
            compteur++;
        }
        return compteur;

    }

    void nextStep() {
        /**
         * Fonction permettant d'actualiser la grille entre deux étapes. Elle ne gère pas l'implémentation graphique
         */
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if(this.tab[i][j].getEtatCourant() != 0) {
                    if (getNbVoisinDiff(this.tab[i][j]) >= this.K) {
                        CelluleGeneral newLocation = this.file.remove();
                        CelluleGeneral sent = newLocation;
                        while (this.getNbVoisinDiff(newLocation) >= this.K) {
                            this.file.add(newLocation);
                            newLocation = this.file.remove();
                            if (newLocation == sent){
                                break;
                            }
                        }
                        this.tab[newLocation.getX()][newLocation.getY()] = newLocation;
                        this.tab[newLocation.getX()][newLocation.getY()].setEtatCourant(this.tab[i][j].getEtatCourant());
                        this.tab[i][j].setEtatCourant(0);
                        this.file.add(this.tab[i][j]);
                    }
                }
            }
        }
    }

    @Override
    public void next() {
        /**
         * Implémentation de la méthode next. A partir de l'actualisation de la grille, gère son implémentation graphique
         */
        this.window.reset();
        this.setNbGeneration(this.getNbGeneration() + 1);
        this.nextStep();
        this.affichageGraphique();
    }

    @Override
    public void restart() {
        /**
         * Implémentation de la méthode restart permettant de réinisialiser la simulation
         */
        this.window.reset();
        this.setNbGeneration(0);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.tab[i][j] = new CelluleGeneral(i,j,this.nbEtat, this.init[i][j]);
                this.window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i, Color.BLACK, this.tableCouleur[this.tab[i][j].getEtatCourant()], 10));
            }
        }
        this.file= new LinkedList<>();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if(this.tab[i][j].getEtatCourant() == 0){
                    this.file.add(this.tab[i][j]);
                }
            }
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :" + this.getNbGeneration()));
    }
}
