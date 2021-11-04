package JeuDeLaVie;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class GrilleSchelling extends GrilleCelluleGeneral implements Simulable {

    private final Color[] table_couleur = {Color.WHITE, Color.CYAN, Color.BLUE, Color.GREEN,Color.PINK, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK};
    private int nbEtat;
    private int[][] init;
    private Queue<CelluleGeneral> file = new LinkedList<>();
    private int K;

    public GrilleSchelling(int n, int m, GUISimulator window, int nbGeneration, int nbEtat, int[][] init, boolean start, Queue<CelluleGeneral> file, int K, CelluleGeneral[][] tab) {
        super(n, m, window, nbGeneration);
        this.tab = tab;
        this.nbEtat = nbEtat;
        this.init = init;
        this.K=K;
        if(start){
            this.restart();
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++) {
                    if(this.tab[i][j].getEtatCourant() == 0){
                        this.file.add(this.tab[i][j]);
                    }
                }
            }
        }else{
            this.restartViergeSchelling(tab);
            this.file = file;
        }
    }

    public int getNbVoisinDiff(CelluleGeneral cel){
        int sameState =cel.getEtatCourant();
        int x = cel.getX();
        int y = cel.getY();
        int compteur = 0;
        if(this.tab[(x-1+this.n)%this.n][(y-1 +this.n)%this.m].getEtatCourant()!= sameState && this.tab[(x-1+this.n)%this.n][(y-1 +this.n)%this.m].getEtatCourant()!= 0 ){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y-1+this.n)%this.m].getEtatCourant()!= sameState && this.tab[(x)%this.n][(y-1+this.n)%this.m].getEtatCourant() != 0){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y-1+this.n)%this.m].getEtatCourant()!= sameState && this.tab[(x+1)%this.n][(y-1+this.n)%this.m].getEtatCourant() != 0){
            compteur+=1;
        }
        if(this.tab[(x-1+this.n)%this.n][(y)%this.m].getEtatCourant()!= sameState && this.tab[(x-1+this.n)%this.n][(y)%this.m].getEtatCourant() != 0){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y)%this.m].getEtatCourant()!= sameState && this.tab[(x+1)%this.n][(y)%this.m].getEtatCourant()!= 0){
            compteur+=1;
        }
        if(this.tab[(x-1+this.n)%this.n][(y+1)%this.m].getEtatCourant()!= sameState && this.tab[(x-1+this.n)%this.n][(y+1)%this.m].getEtatCourant()!= 0){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y+1)%this.m].getEtatCourant()!= sameState && this.tab[(x)%this.n][(y+1)%this.m].getEtatCourant()!= 0){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y+1)%this.m].getEtatCourant()!= sameState && this.tab[(x+1)%this.n][(y+1)%this.m].getEtatCourant()!= 0){
            compteur+=1;
        }
        return compteur;

    }

    GrilleSchelling nextStep() {
        GrilleSchelling new_grille = new GrilleSchelling(this.n, this.m, this.window, this.getNbGeneration(), this.nbEtat, this.init, false,this.file, this.K, this.tab);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if(this.tab[i][j].getEtatCourant() != 0) {
                    if (getNbVoisinDiff(this.tab[i][j]) >= new_grille.K) {
                        CelluleGeneral newLocation = new_grille.file.remove();
                        while (this.getNbVoisinDiff(newLocation) >= new_grille.K) {
                            new_grille.file.add(newLocation);
                            newLocation = new_grille.file.remove();
                        }
                        new_grille.tab[newLocation.getX()][newLocation.getY()] = newLocation;
                        new_grille.tab[newLocation.getX()][newLocation.getY()].setEtatCourant(this.tab[i][j].getEtatCourant());
                        this.tab[i][j].setEtatCourant(0);
                        new_grille.tab[i][j].setEtatCourant(0);
                        new_grille.file.add(this.tab[i][j]);
                    } else {
                        new_grille.tab[i][j].setEtatCourant((this.tab[i][j].getEtatCourant()));
                    }
                }
            }
        }
        return new_grille;
    }

    @Override
    public void next() {
        this.setNbGeneration(this.getNbGeneration() + 1);
        GrilleSchelling new_grille_cellule=this.nextStep();
        this.tab = new_grille_cellule.tab;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i,  Color.BLACK, this.table_couleur[this.tab[i][j].getEtatCourant()], 10));
            }
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :"+ this.getNbGeneration()));
    }

    @Override
    public void restart() {
        super.restartVierge();
        this.setNbGeneration(0);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.tab[i][j] = new CelluleGeneral(i,j,this.nbEtat, this.init[i][j]);
                this.window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i, Color.BLACK, this.table_couleur[this.tab[i][j].getEtatCourant()], 10));
            }
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :" + this.getNbGeneration()));
    }


    protected void restartViergeSchelling(CelluleGeneral[][] tab) {
        super.restartVierge();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.tab[i][j] = new CelluleImigration(i, j, this.nbEtat, tab[i][j].getEtatCourant());
            }
        }
    }
}
