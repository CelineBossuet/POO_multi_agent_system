package JeuDeLaVie;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import java.awt.*;

public class GrilleCelluleImmigration extends GrilleCelluleGeneral implements Simulable {

    private final Color[] table_couleur = {Color.WHITE, Color.CYAN, Color.BLUE, Color.GREEN,Color.PINK, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK};
    private final int nbEtat;
    private final int[][] init;


    public GrilleCelluleImmigration(int n, int m, GUISimulator window,int nbGeneration, int nbEtat, int[][] init, boolean start) {
        super(n, m, window, nbGeneration);
        this.init = init;
        this.nbEtat = nbEtat;
        this.tab = new CelluleGeneral[n][m];
        if(this.table_couleur.length < nbEtat){
            System.out.println("Trop d'état pour l'implémentation graphique");
            System.exit(1);
        }
        if(start){
            this.restart();
        }else{
            this.restartVierge();
        }
    }

    public int getNbVoisin(CelluleImigration cel){
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
        GrilleCelluleImmigration new_grille = new GrilleCelluleImmigration(this.n, this.m, this.window, this.getNbGeneration(), this.nbEtat, this.init, false);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if(getNbVoisin((CelluleImigration)this.tab[i][j]) >=3){
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
        this.setNbGeneration(this.getNbGeneration() + 1);
        GrilleCelluleImmigration new_grille_cellule=this.nextStep();
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
                this.tab[i][j] = new CelluleImigration(i,j,this.nbEtat, this.init[i][j]);
                this.window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i, Color.BLACK, this.table_couleur[this.tab[i][j].getEtatCourant()], 10));
            }
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :" + this.getNbGeneration()));
    }

    @Override
    protected void restartVierge() {
        super.restartVierge();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.tab[i][j] = new CelluleImigration(i, j, this.nbEtat, 0);
            }
        }
    }
}
