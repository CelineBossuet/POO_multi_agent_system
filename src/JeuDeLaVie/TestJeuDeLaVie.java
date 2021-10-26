package JeuDeLaVie;

import gui.GUISimulator;

import java.awt.*;

public class TestJeuDeLaVie {


    public static void main(String[] args) {
        int n = 500;
        int m = 500;
        int nSim= 500/10;
        int mSim = 500/10;
        //int[][] init = {{2,2,2,2,2,2,2,2},{2,3,4,5,6,7,8,9,10}}; //etat stationnaire
        //int[][] init = {{2,2,2},{2,3,4}}; //etat clignotant
        //int[][] init = {{25,25,25,25,25,25,25,25},{22,23,24,25,26,27,28,29,30}}; //pareil mais centré
        int[][] init = {{1,2,1,1,3,4,8,2,9,20,40,3,15,10,12,45,3,21,49,11,2,19},{4,2,10,15,14,40,30,3,13,42,15,18,1,5,49,17,6,35,32,14,4,5}};//Etat stationnaire à 234 générations
        GUISimulator gui = new GUISimulator(n+10, m+100, Color.BLUE);
        gui.setSimulable(new GrilleCellule(nSim, mSim, gui, true, init,0));
    }

}
