package JeuDeLaVie;

import gui.GUISimulator;

import java.awt.*;
import java.util.Random;

public class TestjeuImmigration {

    public static void main(String[] args) {
        int n = 500;
        int m = 500;
        int nSim= 500/10;
        int mSim = 500/10;
        int nbEtat= 6;
        int[][] init = new int[nSim][mSim];
        for (int i = 0; i < nSim; i++) {
            for (int j = 0; j < mSim; j++) {
                Random random = new Random();
                init[i][j] = random.nextInt(nbEtat);
            }
        }
        GUISimulator gui = new GUISimulator(n+10, m+100, Color.BLUE);
        gui.setSimulable(new GrilleCelluleImmigration(nSim, mSim, gui, 0, nbEtat,init, true));
    }
}
