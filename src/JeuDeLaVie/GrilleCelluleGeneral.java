package JeuDeLaVie;

import gui.GUISimulator;

public class GrilleCelluleGeneral {
    protected GUISimulator window;
    protected int n;
    protected int m;
    private int nbGeneration;
    protected CelluleGeneral[][] tab;

    public GrilleCelluleGeneral(int n, int m, GUISimulator window, int nbGeneration) {
        this.n = n;
        this.m = m;
        this.window = window;
        this.nbGeneration = nbGeneration;
        this.tab = new CelluleGeneral[n][m];
    }

    public void setNbGeneration(int nbGeneration) {
        this.nbGeneration = nbGeneration ;
    }

    public int getNbGeneration(){
        return nbGeneration;
    }

    void restartVierge() {
        this.window.reset();
    }
}
