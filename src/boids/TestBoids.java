package boids;

import events.*;
import gui.GUISimulator;

import java.awt.*;

public class TestBoids {

    public static void main(String[] args){
        GUISimulator gui = new GUISimulator(500, 500, Color.BLUE);
        BoidSimulator simulator = new BoidSimulator(gui, 0, 50);

        EventManager manager = new EventManager();
        manager.addEvent(simulator);

        gui.setSimulable(manager);
    }
}
