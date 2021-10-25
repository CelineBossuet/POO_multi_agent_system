package JeuDeLaVie;

import gui.GUISimulator;
import gui.Simulable;

public class GrilleCellule implements Simulable {

    private int n;
    private int m;
    private Cellule[][] tab;
    private GUISimulator window;


    public GrilleCellule(int n, int m, GUISimulator window) {
        this.n = n;
        this.m = m;
        this.tab = new Cellule[n][m];
        this.window = window;
    }

    public int getNbVoisin(Cellule cel){
        int x = cel.getX();
        int y = cel.getY();
        int compteur = 0;
        if(this.tab[(x-1)%this.n][(y-1)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x)%this.n][(y-1)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y-1)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x-1)%this.n][(y)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x+1)%this.n][(y)%this.m].isEstVivante()){
            compteur+=1;
        }
        if(this.tab[(x-1)%this.n][(y+1)%this.m].isEstVivante()){
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
        GrilleCellule new_grille = new GrilleCellule(this.n, this.m, GUISimulator window);
        for (int i = 0; i < this.n; i++){
            for(int j = 0; j < this.m; j++){
                if(this.tab[i][j].isEstVivante()){
                    if(getNbVoisin(this.tab[i][j]) < 2){
                        new_grille.tab[i][j].setEstVivant(false);

                    }
                    else if(getNbVoisin(this.tab[i][j]) == 2 || getNbVoisin(this.tab[i][j]) ==3){
                        new_grille.tab[i][j].setEstVivant(true);
                    }
                    else if(getNbVoisin(this.tab[i][j]) > 4) {
                        new_grille.tab[i][j].setEstVivant(false);
                    }
                }
                else{
                    if(getNbVoisin(this.tab[i][j]) == 3){
                        new_grille.tab[i][j].setEstVivant(true);
                    }
                    else{
                        new_grille.tab[i][j].setEstVivant(false);
                    }
                }
            }
        }
        return new_grille;
    }


    @Override
    public void next() {
        GrilleCellule new_grille_cellule=this.nextStep();

    }

    @Override
    public void restart() {

    }
}
