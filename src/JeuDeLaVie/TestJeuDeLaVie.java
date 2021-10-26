package JeuDeLaVie;

import gui.GUISimulator;

import java.awt.*;

public class TestJeuDeLaVie {


    private static Object Cellule;

    public static void main(String[] args) {
        int n = 500;
        int m = 500;
        int nSim= 500/10;
        int mSim = 500/10;
        int[][] init = {{2,2,2,2,2,2,2,2},{2,3,4,5,6,7,8,9,10}}; //etat stationnaire
        //int[][] init = {{2,2,2},{2,3,4}}; //etat clignotant
        GUISimulator gui = new GUISimulator(n+10, m+100, Color.BLUE);
        gui.setSimulable(new GrilleCellule(nSim, mSim, gui, true, init,0));
    }

}
