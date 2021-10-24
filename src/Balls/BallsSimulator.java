package Balls;

import gui.Simulable;
import gui.Oval;
import gui.GUISimulator;

import java.awt.*;


public class BallsSimulator implements Simulable {

    private final Balls balls;
    private final GUISimulator window;

    public BallsSimulator(GUISimulator gui, Balls balls) {
        this.balls = balls;
        this.window =gui;
    }

    @Override
    public void next(){
        balls.translate(5,10);
        window.reset();
        window.addGraphicalElement(new Oval(balls.getBall1().x, balls.getBall1().y, Color.BLUE, Color.BLUE, 5));
        window.addGraphicalElement(new Oval(balls.getBall2().x, balls.getBall2().y, Color.RED, Color.RED, 5));
        window.addGraphicalElement(new Oval(balls.getBall3().x, balls.getBall3().y, Color.WHITE, Color.WHITE, 5));
    }

    @Override
    public void restart() {
        balls.reInit();
    }
}
