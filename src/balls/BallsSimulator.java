package Balls;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;

    public BallsSimulator(GUISimulator gui){
        this.gui = gui;
        balls = new Balls(5);
    }

    @Override
    public void next(){
        int width = this.gui.getPanelWidth();
        int height = this.gui.getPanelHeight();

        Ball[] balls = this.balls.getBalls();

        for(int i = 0; i < balls.length; i++){
            if(balls[i].getY() > height || balls[i].getY() < 0){
                balls[i].setDy(-balls[i].getDy());
            }

            if(balls[i].getX() > width || balls[i].getX() < 0){
                balls[i].setDx(-balls[i].getDx());
            }

           balls[i].translate(balls[i].getDx(), balls[i].getDy());
        }

        gui.reset();
        for(int i = 0; i < balls.length; i++) {
            gui.addGraphicalElement(new Oval(balls[i].getX(), balls[i].getY(), Color.WHITE, Color.WHITE, 10));
        }
    }

    @Override
    public void restart(){
        balls.reInit();
        gui.reset();

        Ball[] balls = this.balls.getBalls();

        for(int i = 0; i < balls.length; i++) {
            balls[i].setDx(10);
            balls[i].setDy(10);
            gui.addGraphicalElement(new Oval(balls[i].getX(), balls[i].getY(), Color.WHITE, Color.WHITE, 10));
        }
    }
}