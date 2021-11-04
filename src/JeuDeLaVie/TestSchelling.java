package JeuDeLaVie;

import gui.GUISimulator;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TestSchelling {


    public static void main(String[] args) {

        int K = 2;
        int n = 500;
        int m = 500;
        int nSim= 500/10;
        int mSim = 500/10;
        int nbEtat= 3;
        Queue<CelluleGeneral> file = new LinkedList<>();
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
        gui.setSimulable(new GrilleSchelling(nSim, mSim, gui, 0, nbEtat,init, true, file, K, new CelluleGeneral[nSim][mSim]));

    }
}
