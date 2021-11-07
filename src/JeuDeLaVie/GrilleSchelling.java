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
        }
    }

    public int getNbVoisinDiff(CelluleGeneral cel){
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
        this.window.reset();
        this.setNbGeneration(this.getNbGeneration() + 1);
        this.nextStep();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i,  Color.BLACK, this.table_couleur[this.tab[i][j].getEtatCourant()], 10));
            }
        }
        window.addGraphicalElement(new Text(10+10*this.m/2, 50+10*this.n, Color.BLACK, "Génération numéro :"+ this.getNbGeneration()));
        System.out.println(this.file.size());
    }

    @Override
    public void restart() {
        this.window.reset();
        this.setNbGeneration(0);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.tab[i][j] = new CelluleGeneral(i,j,this.nbEtat, this.init[i][j]);
                this.window.addGraphicalElement(new Rectangle(10+10*j, 10+10* i, Color.BLACK, this.table_couleur[this.tab[i][j].getEtatCourant()], 10));
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
        System.out.println(this.file.size());
    }
}
