package Balls;

import java.awt.Point;

public class Balls {

    private final Point ball1;
    private final Point ball2;
    private final Point ball3;

    public Point getBall1() {
        return ball1;
    }

    public Point getBall2() {
        return ball2;
    }

    public Point getBall3() {
        return ball3;
    }

    public Balls() {
        ball1 = new Point(0,0);
        ball2 = new Point(66,66);
        ball3 = new Point(6, 12);
    }

    @Override
    public String toString() {
        return "Balls{" +
                "ball1= (" + ball1.x + "," + ball1.y + ")" +
                ", ball2=(" + ball2.x + "," + ball2.y + ")" +
                ", ball3=(" + ball3.x + "," + ball3.y + ")" +
                '}';
    }

    void translate(int dx, int dy){
        ball1.x += dx ;
        ball1.y += dy;
        ball2.x += dx;
        ball2.y += dy;
        ball3.x += dx;
        ball3.y += dy;
    }

    void reInit(){
        ball1.x = 0;
        ball1.y = 0;
        ball2.y = 66;
        ball2.x = 66;
        ball3.x = 6;
        ball3.y = 12;
    }

}
