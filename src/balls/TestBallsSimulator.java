package Balls;

import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;

import java.awt.*;

public class TestBallsSimulator {
    public static void main(String[] args){
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        //gui.addGraphicalElement(new Rectangle(0, 0, Color.BLUE, Color.BLACK, 500));
        gui.setSimulable(new BallsSimulator(gui));
    }
}
