package balls;

import java.awt.Point;
public class Balls {
    private Point[] balls= new Point[3];
    private Point[] init =new Point[3];

    public Point[] getBalls(){
        return balls;
    }
    public Balls(){
        balls[0]=new Point(10, 10);
        init[0] = new Point(10, 10);
        balls[1]=new Point(10, 50);
        init[1] = new Point(10, 50);
        balls[2]=new Point(50, 10);
        init[2] = new Point(50, 10);

    }

    void translate(int dx, int dy){
        for (int i=0; i<balls.length; i++){
            balls[i].x+=dx;
            balls[i].y+=dy;
        }
    }
    void reInit(){
        for (int i=0; i<balls.length; i++){
            balls[i].x=init[i].x;
            balls[i].y=init[i].y;
        }
    }

    @Override
    public String toString(){
        String res="";
        for (int i=0; i< balls.length; i++){
            res+= "Point " + i + " x=" + balls[i].getX() + ", y=" + balls[i].getY() + "\n";
        }
        return res;
    }
}
