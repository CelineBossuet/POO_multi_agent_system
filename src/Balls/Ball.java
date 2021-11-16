package Balls;

import java.awt.Point;

public class Ball {
    private Point position;
    private int dx = 10;
    private int dy = 10;

    public Ball(int x, int y){
        this.position = new Point(x, y);
    }

    public int getX(){
        return this.position.x;
    }

    public int getY(){
        return this.position.y;
    }

    public int getDx(){
        return this.dx;
    }

    public int getDy(){
        return this.dy;
    }

    public void setDx(int dx){
        this.dx = dx;
    }

    public void setDy(int dy){
        this.dy = dy;
    }

    public void translate(int dx, int dy){
        this.position.x += dx;
        this.position.y += dy;
    }

    public void setLocation(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }
}
