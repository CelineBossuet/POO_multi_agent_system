package JeuDeLaVie;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public class GrilleCellule implements Simulable {

    private final int n;
    private final int m;
    private Cellule[][] tab;
    private final GUISimulator window;
    private  final int[][] init;


    public GrilleCellule(int n, int m, gui.GUISimulator window, boolean start, int[][] init) {
        this.n = n;
        this.m = m;
        this.tab = new Cellule[n][m];
        this.window = window;
        this.init = init;
        if(start) {
            this.restart();
        }else{
            this.restartVierge();
        }
    }

    private void restartVierge() {
        this.window.reset();
        for(int i = 0; i< this.n; i++){
            for(int j = 0; j < this.m; j++){
                this.tab[i][j] = new Cellule(i,j,false);
                window.addGraphicalElement(new Rectangle(10*i, 10* j, Color.BLACK, Color.WHITE, 10));
            }
        }
    }

    public int getNbVoisin(Cellule cel){
        int x = cel.getX();
        int y = cel.getY();
        int compteur = 0;
        if(this.tab[(x-1+this.n)%this.n][(y-1 +this.n)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y-1+this.n)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y-1+this.n)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x-1+this.n)%this.n][(y)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x-1+this.n)%this.n][(y+1)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y+1)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y+1)%this.m].isEstVivante()){
            compteur+=1;
        }
        return compteur;
    }

    GrilleCellule nextStep(){
        GrilleCellule new_grille = new GrilleCellule(this.n, this.m, this.window, false, this.init);
        for (int i = 0; i < this.n; i++){
            for(int j = 0; j < this.m; j++){
                if(this.tab[i][j].isEstVivante()){
                    if(getNbVoisin(this.tab[i][j]) < 2){
                        new_grille.tab[i][j].setEstVivant(false);

                    }
                    else if(getNbVoisin(this.tab[i][j]) == 2 || getNbVoisin(this.tab[i][j]) ==3){
                        new_grille.tab[i][j].setEstVivant(true);
                    }
                    else if(getNbVoisin(this.tab[i][j]) > 3) {
                        new_grille.tab[i][j].setEstVivant(false);
                    }
                }
                else{
                    new_grille.tab[i][j].setEstVivant(getNbVoisin(this.tab[i][j]) == 3);
                }
            }
        }
        return new_grille;
    }


    @Override
    public void next() {
        GrilleCellule new_grille_cellule=this.nextStep();
        this.tab = new_grille_cellule.tab;
        for (int i = 0; i < this.n ; i++) {
            for (int j = 0; j < this.m; j++) {
                if(this.tab[i][j].isEstVivante()){
                    window.addGraphicalElement(new Rectangle(10*j, 10* i, Color.BLACK, Color.BLACK, 10));
                }else{
                    window.addGraphicalElement(new Rectangle(10*j, 10* i, Color.BLACK, Color.WHITE, 10));
                }
            }

        }
    }

    @Override
    public void restart() {
        this.window.reset();
        for(int i = 0; i< this.n; i++){
            for(int j = 0; j < this.m; j++){
                this.tab[i][j] = new Cellule(i,j,false);
                window.addGraphicalElement(new Rectangle(10*j, 10* i, Color.BLACK, Color.WHITE, 10));
            }
        }
        for (int i = 0; i < this.init[0].length; i++) {
            this.tab[this.init[0][i]][this.init[1][i]].setEstVivant(true);
            window.addGraphicalElement(new Rectangle(10*this.init[1][i], 10* this.init[0][i], Color.BLACK, Color.BLACK, 10));
        }
    }
}
