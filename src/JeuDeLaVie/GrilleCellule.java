package JeuDeLaVie;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import java.awt.*;

public class GrilleCellule extends GrilleCelluleGeneral implements Simulable {

    private  final int[][] init;
    private int nbGeneration;


    public GrilleCellule(int n, int m, GUISimulator window, boolean start, int[][] init, int nbGeneration) {
        super(n, m, window, nbGeneration);
        this.init = init;
        this.tab = new Cellule[n][m];
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
        super.restartVierge();
        for(int i = 0; i< this.n; i++){
            for(int j = 0; j < this.m; j++){
                this.tab[i][j] = new Cellule(i,j,0);
                window.addGraphicalElement(new Rectangle(10+10*i, 10+10* j, Color.BLACK, Color.WHITE, 10));
            }
        }
    }

    public int getNbVoisin(Cellule cel){
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
        this.setNbGeneration(this.getNbGeneration() + 1);
        GrilleCellule new_grille_cellule=this.nextStep();
        this.tab = new_grille_cellule.tab;
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
