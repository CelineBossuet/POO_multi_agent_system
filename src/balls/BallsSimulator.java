package balls;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;

    public BallsSimulator(Balls balls, GUISimulator gui){
        this.balls=balls;
        this.gui=gui;
    }
    @Override
    public void next(){
        balls.translate(10,10);
        gui.reset();

        for (int i=0; i<balls.getBalls().length; i++){
            gui.addGraphicalElement(new Oval(balls.getBalls()[i].x, balls.getBalls()[i].y, Color.CYAN, Color.CYAN, 10));
        }
    }
    @Override
    public void restart(){
        balls.reInit();
        gui.reset();
        for (int i=0; i<balls.getBalls().length; i++){
            gui.addGraphicalElement(new Oval(balls.getBalls()[i].x, balls.getBalls()[i].y, Color.CYAN, Color.CYAN, 10));
        }
    }
}
