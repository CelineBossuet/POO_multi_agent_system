package JeuDeLaVie;

import gui.GUISimulator;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TestSchelling {

    /**
     * Fonction permettant de tester le modèle schelling à partir d'une génération aléatoire. Il est possible de modifier certains paramètres pour mieux comprendre le modèle.
     */

    public static void main(String[] args) {

        int K = 3;
        int n = 500;
        int m = 500;
        int nSim= 500/10;
        int mSim = 500/10;
        int nbEtat= 3;
        int[][] init = new int[nSim][mSim];
        for (int i = 0; i < nSim; i++) {
            for (int j = 0; j < mSim; j++) {
                Random random = new Random();
                if(random.nextInt(2)==0){
                    init[i][j] = 0;
                }else{
                    Random random2 = new Random();
                    init[i][j] = random2.nextInt(nbEtat) + 1;
                }

            }
        }
        GUISimulator gui = new GUISimulator(n+10, m+100, Color.BLUE);
        gui.setSimulable(new GrilleSchelling(nSim, mSim, gui, 0, nbEtat,init, true, K, new CelluleGeneral[nSim][mSim]));

    }
}
